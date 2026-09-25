CREATE TABLE cafe_table (
                            id BIGSERIAL PRIMARY KEY,
                            cafe_id BIGINT NOT NULL REFERENCES cafe(id) ON DELETE CASCADE,
                            table_number VARCHAR(50) NOT NULL,
                            qr_token VARCHAR(255) NOT NULL UNIQUE,
                            seats INT
);

CREATE TABLE menu_category (
                               id BIGSERIAL PRIMARY KEY,
                               cafe_id BIGINT NOT NULL REFERENCES cafe(id) ON DELETE CASCADE,
                               name VARCHAR(255) NOT NULL,
                               sort_order INT DEFAULT 0
);

CREATE TABLE menu_item (
                           id BIGSERIAL PRIMARY KEY,
                           category_id BIGINT NOT NULL REFERENCES menu_category(id) ON DELETE CASCADE,
                           name VARCHAR(255) NOT NULL,
                           description TEXT,
                           price NUMERIC(10, 2) NOT NULL,
                           photo_url VARCHAR(500),
                           is_available BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE INDEX idx_cafe_table_cafe ON cafe_table(cafe_id);
CREATE INDEX idx_menu_category_cafe ON menu_category(cafe_id);
CREATE INDEX idx_menu_item_category ON menu_item(category_id);