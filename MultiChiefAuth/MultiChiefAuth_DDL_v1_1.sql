CREATE TABLE "role"(
id SERIAL PRIMARY KEY,
rank VARCHAR(25) NOT NULL,
UNIQUE(rank),
CHECK (rank !='')
);

CREATE TABLE "app_user"(
id SERIAL PRIMARY KEY,
login VARCHAR(25) NOT NULL,
password VARCHAR(255) NOT NULL,
email VARCHAR(80),
role_id INTEGER REFERENCES "role"(id) ON DELETE SET NULL,
status VARCHAR(25),
UNIQUE (login),
CHECK (login !=''),
CHECK (password !='')
);
