CREATE TABLE IF NOT EXISTS ingredient_category
(
    id              BIGSERIAL PRIMARY KEY,
    ingredient_name VARCHAR(255) NOT NULL,
    CONSTRAINT ingredient_name_uniq UNIQUE (ingredient_name)
);

CREATE INDEX IF NOT EXISTS idx_ingredient_name
    ON ingredient_category (ingredient_name);
