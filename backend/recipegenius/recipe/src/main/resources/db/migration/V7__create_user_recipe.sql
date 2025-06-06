CREATE TABLE IF NOT EXISTS user_recipe
(
    user_id       VARCHAR(36) NOT NULL,
    recipe_id BIGINT      NOT NULL,
    PRIMARY KEY (user_id, recipe_id)
);