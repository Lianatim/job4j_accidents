CREATE TABLE IF NOT EXISTS type (
 id serial PRIMARY KEY,
 name VARCHAR
);

CREATE TABLE IF NOT EXISTS rule (
 id SERIAL PRIMARY KEY,
 name VARCHAR
);

CREATE TABLE IF NOT EXISTS accident (
 id serial PRIMARY KEY,
 name VARCHAR,
 text VARCHAR,
 address VARCHAR,
 type_id INT REFERENCES type(id)
);

CREATE TABLE IF NOT EXISTS rule_accident (
 id SERIAL PRIMARY KEY,
 rule_id INT REFERENCES rule(id),
 accident_id INT REFERENCES accident(id)
);