import ProductItem from "./ProductItem";

export default function ProductList({
                                        products,
                                        isAdmin,
                                        onDelete,
                                    }) {
    if (!products.length)
        return <p className="empty-text">No products available.</p>;

    return (
        <div className="product-grid">
            {products.map((product) => (
                <ProductItem
                    key={product.id}
                    product={product}
                    isAdmin={isAdmin}
                    onDelete={onDelete}
                />
            ))}
        </div>
    );
}
