DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id serial PRIMARY KEY,
    login character varying(50) NOT NULL unique,
    password text NOT NULL,
    role character varying(2),
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now()
);