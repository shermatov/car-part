CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,

                          name VARCHAR(150) NOT NULL,
                          description VARCHAR(500),

                          quantity INT NOT NULL,
                          price DOUBLE PRECISION NOT NULL,

                          shop_id BIGINT NOT NULL,
                          category_id BIGINT,
                          brand_id BIGINT,

                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          deleted_at TIMESTAMP NULL,

                          CONSTRAINT fk_product_shop
                              FOREIGN KEY (shop_id)
                                  REFERENCES shops(id),

                          CONSTRAINT fk_product_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id),

                          CONSTRAINT fk_product_brand
                              FOREIGN KEY (brand_id)
                                  REFERENCES brands(id)
);

CREATE INDEX idx_product_shop_id ON products(shop_id);
CREATE INDEX idx_product_category_id ON products(category_id);
CREATE INDEX idx_product_brand_id ON products(brand_id);
