CREATE USER populi WITH PASSWORD 'populi';
CREATE DATABASE populi;
GRANT ALL PRIVILEGES ON DATABASE populi to populi;


CREATE EXTENSION postgis;
CREATE EXTENSION postgis_topology;
CREATE EXTENSION fuzzystrmatch;
CREATE EXTENSION postgis_tiger_geocoder;
