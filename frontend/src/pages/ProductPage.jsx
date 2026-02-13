import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import productApi from "../api/productApi";

import {
    Box,
    Typography,
    Button,
    Paper,
    Divider,
    Chip,
} from "@mui/material";

import { SnackbarProvider, useSnackbar, closeSnackbar } from "notistack";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";
import ConfirmDialog from "../components/common/ConfirmDialog";

function ProductPageContent({ isAdmin }) {
    const { productId } = useParams();
    const navigate = useNavigate();

    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);

    const [confirmOpen, setConfirmOpen] = useState(false);

    const { enqueueSnackbar } = useSnackbar();

    const loadProduct = async () => {
        try {
            setLoading(true);
            const res = await productApi.getProductById(productId);
            setProduct(res.data);
        } catch (err) {
            console.error(err);
            enqueueSnackbar("Failed to load product", { variant: "error" });
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadProduct();
    }, [productId]);

    const handleDelete = async () => {
        if (!product) return;

        try {
            await productApi.deleteProduct(product.id);
            enqueueSnackbar("Product deleted", { variant: "warning" });
            navigate(-1);
        } catch (err) {
            console.error(err);
            enqueueSnackbar("Failed to delete product", { variant: "error" });
        } finally {
            setConfirmOpen(false);
        }
    };

    if (loading) {
        return <Typography>Loading...</Typography>;
    }

    if (!product) {
        return (
            <Box sx={{ textAlign: "center", py: 10 }}>
                <Typography variant="h6" color="text.secondary">
                    Product not found
                </Typography>
            </Box>
        );
    }

    return (
        <Box>
            {/* Header */}
            <Box
                sx={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    mb: 3,
                }}
            >
                <Typography variant="h4">{product.name}</Typography>

                <Button
                    variant="outlined"
                    onClick={() => navigate(-1)}
                >
                    Back
                </Button>
            </Box>

            {/* Product Details */}
            <Paper sx={{ p: 3 }}>
                <Typography variant="body1" sx={{ mb: 2 }}>
                    {product.description}
                </Typography>

                <Divider sx={{ mb: 2 }} />

                <Typography variant="h6" sx={{ mb: 1 }}>
                    €{product.price}
                </Typography>

                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                    Quantity: {product.quantity}
                </Typography>

                <Box sx={{ display: "flex", gap: 1, mb: 2 }}>
                    {product.category && (
                        <Chip label={product.category} />
                    )}
                    {product.brand && (
                        <Chip label={product.brand} />
                    )}
                </Box>

                {isAdmin && (
                    <Box sx={{ display: "flex", gap: 2 }}>
                        <Button variant="contained">
                            Edit
                        </Button>

                        <Button
                            variant="outlined"
                            color="error"
                            onClick={() => setConfirmOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                )}
            </Paper>

            {/* Confirm Delete */}
            <ConfirmDialog
                open={confirmOpen}
                title="Delete Product"
                message={`Are you sure you want to delete "${product.name}"?`}
                onConfirm={handleDelete}
                onClose={() => setConfirmOpen(false)}
            />
        </Box>
    );
}

export default function ProductPage(props) {
    return (
        <SnackbarProvider
            maxSnack={3}
            anchorOrigin={{ vertical: "top", horizontal: "right" }}
            autoHideDuration={3000}
            action={(snackbarId) => (
                <IconButton
                    size="small"
                    color="inherit"
                    onClick={(event) => {
                        event.stopPropagation();
                        closeSnackbar(snackbarId);
                    }}
                >
                    <CloseIcon fontSize="small" />
                </IconButton>
            )}
        >
            <ProductPageContent {...props} />
        </SnackbarProvider>
    );
}