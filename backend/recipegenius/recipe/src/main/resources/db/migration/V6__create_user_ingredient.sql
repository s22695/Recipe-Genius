CREATE TABLE IF NOT EXISTS user_ingredient
(
    user_id       VARCHAR(36) NOT NULL,
    ingredient_id BIGINT      NOT NULL,
    PRIMARY KEY (user_id, ingredient_id)
);