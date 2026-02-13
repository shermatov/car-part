export default function ProductItem({ product, isAdmin, onDelete }) {
    return (
        <div className="product-card">
            <div className="product-body">
                <h3 className="product-title">{product.name}</h3>

                <p className="product-description">
                    {product.description}
                </p>

                <div className="product-meta">
          <span className="product-price">
            €{product.price}
          </span>

                    <span
                        className={
                            product.quantity > 0
                                ? "in-stock"
                                : "out-stock"
                        }
                    >
            {product.quantity > 0
                ? `In stock (${product.quantity})`
                : "Out of stock"}
          </span>
                </div>

                <div className="product-tags">
                    {product.category && <span>{product.category}</span>}
                    {product.brand && <span>{product.brand}</span>}
                </div>

                {isAdmin && (
                    <div className="product-actions">
                        <button className="edit-btn">
                            Edit
                        </button>

                        <button
                            className="delete-btn"
                            onClick={() => onDelete(product.id)}
                        >
                            Delete
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}
