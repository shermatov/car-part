import {
    Typography,
    Box,
    Button,
    TextField,
    Paper,
    InputAdornment,
    TablePagination,
} from "@mui/material";
import { Add, Store, Search } from "@mui/icons-material";
import { useEffect, useState, useMemo } from "react";
import { SnackbarProvider, useSnackbar, closeSnackbar } from "notistack";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";

import shopApi from "../api/shopApi";
import productApi from "../api/productApi";
import ProductList from "../components/product/ProductList";
import ConfirmDialog from "../components/common/ConfirmDialog";

function ShopPageContent({ isAdmin }) {
    const [shop, setShop] = useState(null);
    const [products, setProducts] = useState([]);

    const [search, setSearch] = useState("");

    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    const [selectedProduct, setSelectedProduct] = useState(null);
    const [confirmOpen, setConfirmOpen] = useState(false);

    const { enqueueSnackbar } = useSnackbar();

    const loadData = async () => {
        try {
            const shopRes = await shopApi.getMyShop();
            const shopData = Array.isArray(shopRes.data)
                ? shopRes.data[0]
                : shopRes.data;

            setShop(shopRes.data);

            if (shopData?.id) {
                const productRes = await productApi.getProductsByShop(shopData.id);
                setProducts(productRes.data || []);
            }
        } catch {
            enqueueSnackbar("Failed to load shop data", { variant: "error" });
        }
    };

    useEffect(() => {
        loadData();
    }, []);

    const filteredProducts = useMemo(
        () =>
            products.filter((p) =>
                p.name.toLowerCase().includes(search.toLowerCase())
            ),
        [products, search]
    );

    const paginatedProducts = useMemo(() => {
        const start = page * rowsPerPage;
        return filteredProducts.slice(start, start + rowsPerPage);
    }, [filteredProducts, page, rowsPerPage]);

    const askDelete = (product) => {
        setSelectedProduct(product);
        setConfirmOpen(true);
    };

    const confirmDelete = async () => {
        if (!selectedProduct) return;
        try {
            await productApi.deleteProduct(selectedProduct.id);
            enqueueSnackbar("Product deleted", { variant: "warning" });
            setConfirmOpen(false);
            setSelectedProduct(null);
            loadData();
        } catch {
            enqueueSnackbar("Failed to delete product", { variant: "error" });
        }
    };

    if (!shop) {
        return (
            <Box sx={{ textAlign: "center", py: 10 }}>
                <Store sx={{ fontSize: 80, color: "text.secondary", mb: 1 }} />
                <Typography variant="h6" color="text.secondary">
                    No shop found
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
                    flexDirection: { xs: "column", sm: "row" },
                    justifyContent: "space-between",
                    alignItems: { xs: "stretch", sm: "center" },
                    mb: 3,
                    gap: 2,
                }}
            >
                <Box>
                    <Typography variant="h4">{shop.name}</Typography>
                    <Typography variant="body2" color="text.secondary">
                        {shop.description}
                    </Typography>
                </Box>

                {isAdmin && (
                    <Button
                        variant="contained"
                        startIcon={<Add />}
                    >
                        Add Product
                    </Button>
                )}
            </Box>

            {/* Search */}
            <Paper sx={{ p: 2, mb: 2 }}>
                <TextField
                    fullWidth
                    placeholder="Search products…"
                    value={search}
                    onChange={(e) => {
                        setSearch(e.target.value);
                        setPage(0);
                    }}
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <Search />
                            </InputAdornment>
                        ),
                    }}
                />
            </Paper>

            {/* Products */}
            {filteredProducts.length === 0 ? (
                <Box sx={{ textAlign: "center", py: 8 }}>
                    <Typography variant="h6" color="text.secondary">
                        No products found
                    </Typography>
                </Box>
            ) : (
                <ProductList
                    products={paginatedProducts}
                    isAdmin={isAdmin}
                    onDelete={askDelete}
                />
            )}

            {/* Pagination */}
            {filteredProducts.length > rowsPerPage && (
                <TablePagination
                    component="div"
                    count={filteredProducts.length}
                    page={page}
                    onPageChange={(_, newPage) => setPage(newPage)}
                    rowsPerPage={rowsPerPage}
                    onRowsPerPageChange={(e) => {
                        setRowsPerPage(parseInt(e.target.value, 10));
                        setPage(0);
                    }}
                    rowsPerPageOptions={[10, 20, 30]}
                />
            )}

            {/* Confirm Delete */}
            <ConfirmDialog
                open={confirmOpen}
                title="Delete Product"
                message={`Are you sure you want to delete "${selectedProduct?.name}"?`}
                onConfirm={confirmDelete}
                onClose={() => setConfirmOpen(false)}
            />
        </Box>
    );
}

export default function ShopPage(props) {
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
            <ShopPageContent {...props} />
        </SnackbarProvider>
    );
}