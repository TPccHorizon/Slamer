-- CREATE USER postgres WITH PASSWORD 'admin';
-- CREATE DATABASE sla_management;

-- CREATE TABLE STATEMENTS

CREATE TABLE sla_user (
  id SERIAL PRIMARY KEY,
  password VARCHAR(255) NOT NULL ,
  salt VARCHAR(255),
  username VARCHAR(255) NOT NULL ,
  party_type VARCHAR(20),
  party_name VARCHAR(255) NOT NULL,
  wallet VARCHAR,
  private_key VARCHAR
);

CREATE TABLE sla (
  id SERIAL PRIMARY KEY,
  title VARCHAR NOT NULL,
  status VARCHAR(20) NOT NULL ,
  service_price NUMERIC NOT NULL ,
  lifecycle_phase VARCHAR(20) NOT NULL ,
  valid_from DATE NOT NULL ,
  valid_to DATE NOT NULL ,
  service_provider_id INTEGER NOT NULL REFERENCES sla_user(id),
  service_customer_id INTEGER NOT NULL REFERENCES sla_user(id),
  monitoring_solution_id INTEGER REFERENCES sla_user(id),
  hash VARCHAR
);

CREATE TABLE service_level_objective (
  id SERIAL PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  sla_id INTEGER NOT NULL REFERENCES sla(id),
  time_unit VARCHAR(10),
  relational_operator CHAR(2),
  slo_type VARCHAR,
  percentage_of_availability NUMERIC CHECK (percentage_of_availability >= 0 and percentage_of_availability <= 1),
  throughput_data_unit VARCHAR(10),
  throughput_data_size NUMERIC ,
  throughput_threshold_value NUMERIC ,
  average_response_time_value NUMERIC,
  accepted BOOLEAN,
  comment VARCHAR
);

CREATE TABLE sla_review (
  id SERIAL PRIMARY KEY,
  property VARCHAR,
  sla_id INTEGER NOT NULL REFERENCES sla(id),
  accepted BOOLEAN,
  comment VARCHAR,
  value VARCHAR,
  value_type VARCHAR
);

CREATE TABLE ganache_url (
  id SERIAL PRIMARY KEY ,
  url VARCHAR
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres
