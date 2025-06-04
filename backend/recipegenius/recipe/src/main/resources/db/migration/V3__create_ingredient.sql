CREATE TABLE IF NOT EXISTS ingredient
(
    id          SERIAL PRIMARY KEY,
    category_id INTEGER      NOT NULL
        REFERENCES ingredient_category (id)
            ON DELETE RESTRICT,
    ingredient_name
                VARCHAR(255) NOT NULL,
    CONSTRAINT ingredient_name_uniq UNIQUE (ingredient_name)
);

CREATE INDEX IF NOT EXISTS idx_ingredient_category
    ON ingredient (category_id);
