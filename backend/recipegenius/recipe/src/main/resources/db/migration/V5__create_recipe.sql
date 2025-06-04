CREATE TABLE IF NOT EXISTS recipe
(
    id          SERIAL PRIMARY KEY,
    creator_id  VARCHAR(36) NOT NULL,
    image_data  BYTEA,
    prep_time   VARCHAR(50),
    difficulty  VARCHAR(30),
    description TEXT,
    steps TEXT
);

CREATE INDEX IF NOT EXISTS idx_recipe_creator
    ON recipe (creator_id);

CREATE TABLE IF NOT EXISTS recipe_ingredient
(
    recipe_id     INTEGER NOT NULL
        REFERENCES recipe (id) ON DELETE CASCADE,
    ingredient_id INTEGER NOT NULL
        REFERENCES ingredient (id) ON DELETE RESTRICT,
    PRIMARY KEY (recipe_id, ingredient_id)
);

CREATE INDEX IF NOT EXISTS idx_recipe_ingr
    ON recipe_ingredient (ingredient_id);
