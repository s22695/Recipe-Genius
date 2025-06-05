CREATE TABLE IF NOT EXISTS ingredient
(
    id          BIGSERIAL PRIMARY KEY,
    category_id BIGINT      NOT NULL
        REFERENCES ingredient_category (id)
            ON DELETE RESTRICT,
    ingredient_name
                VARCHAR(255) NOT NULL,
    UNIQUE (ingredient_name)
);

CREATE INDEX IF NOT EXISTS idx_ingredient_category
    ON ingredient (category_id);
