CREATE DOMAIN uint AS integer CHECK(VALUE >= 0 AND VALUE < 2147483647);

CREATE TABLE vets (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30)
);

CREATE INDEX vets_last_name_idx ON vets (last_name);

CREATE TABLE specialties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(80)
);

CREATE INDEX specialties_name_idx ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id uint NOT NULL,
  specialty_id uint NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (vet_id, specialty_id)
);

CREATE TABLE IF NOT EXISTS types (
  id SERIAL PRIMARY KEY,
  name VARCHAR(80)
);

CREATE INDEX types_name_idx ON types (name);

CREATE TABLE owners (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20)
);

CREATE INDEX owners_last_name_idx ON owners (last_name);

CREATE TABLE pets (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  type_id uint NOT NULL,
  owner_id uint NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES owners(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
);

CREATE INDEX pets_name_idx ON pets (name);

CREATE TABLE visits (
  id SERIAL PRIMARY KEY,
  pet_id uint NOT NULL,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);