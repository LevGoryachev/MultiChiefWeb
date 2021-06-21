CREATE TABLE "app_user"(
id SERIAL PRIMARY KEY,
login VARCHAR(25) NOT NULL,
password VARCHAR(255) NOT NULL,
email VARCHAR(80),
status VARCHAR(25),
UNIQUE (login),
CHECK (login !=''),
CHECK (password !='')
);

CREATE TABLE "role"(
id SERIAL PRIMARY KEY,
role_name VARCHAR(25) NOT NULL,
UNIQUE(role_name),
CHECK (role_name !='')
);

CREATE TABLE "permission"(
id SERIAL PRIMARY KEY,
perm_name VARCHAR(25) NOT NULL,
UNIQUE(perm_name),
CHECK (perm_name !='')
);

CREATE TABLE "user_role"(
app_user_id INTEGER REFERENCES "app_user"(id) ON DELETE CASCADE,
role_id INTEGER REFERENCES "role"(id) ON DELETE CASCADE,
PRIMARY KEY(app_user_id, role_id)
);

CREATE TABLE "role_perm"(
role_id INTEGER REFERENCES "role"(id) ON DELETE CASCADE,
permission_id INTEGER REFERENCES "permission"(id) ON DELETE CASCADE,
PRIMARY KEY(role_id, permission_id)
);