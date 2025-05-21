CREATE SEQUENCE IF NOT EXISTS users_seq INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT PRIMARY KEY                DEFAULT nextval('users_seq'),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    username    VARCHAR(50)              NOT NULL UNIQUE,
    email       VARCHAR(100)             NOT NULL UNIQUE,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50),
    keycloak_id UUID                     NOT NULL UNIQUE,
    enabled     BOOLEAN                  NOT NULL DEFAULT true
);

CREATE INDEX IF NOT EXISTS idx_users_email ON users (email);
CREATE INDEX IF NOT EXISTS idx_users_username ON users (username);

