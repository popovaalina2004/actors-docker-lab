CREATE TABLE IF NOT EXISTS profile (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS actor (
    id_actor BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_year INT NOT NULL,
    id_profile BIGINT NOT NULL,
    FOREIGN KEY (id_profile) REFERENCES profile(id)
    );