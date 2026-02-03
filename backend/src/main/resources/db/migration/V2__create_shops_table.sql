CREATE TABLE shops (
                       id BIGSERIAL PRIMARY KEY,

                       name VARCHAR(150) NOT NULL,
                       address VARCHAR(255),
                       phone VARCHAR(30),
                       description VARCHAR(500),

                       owner_id BIGINT NOT NULL,

                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP NULL,

                       CONSTRAINT fk_shop_owner
                           FOREIGN KEY (owner_id)
                               REFERENCES users(id)
);

CREATE INDEX idx_shop_owner_id ON shops(owner_id);


