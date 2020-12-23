--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.10
-- Dumped by pg_dump version 9.6.10

-- Started on 2019-01-06 20:50:10 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16696)
-- Name: entity; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA entity;


ALTER SCHEMA entity OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12393)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

-- Table: entity.document_type

-- DROP TABLE entity.document_type;

CREATE TABLE entity.document_type
(
  id character(1) NOT NULL,
  description character varying(15),
  CONSTRAINT pk_document_type PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entity.document_type
  OWNER TO postgres;

-- Table: entity."user"

-- DROP TABLE entity."user";

CREATE TABLE entity."user"
(
  id serial NOT NULL,
  name character varying(150),
  surname character varying(150),
  birthdate date,
  gender character(1),
  address character varying(200),
  email character varying(100),
  phone character varying(15),
  document_type character(1),
  document_number character varying(15),
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT fk_document_type FOREIGN KEY (document_type)
      REFERENCES entity.document_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entity."user"
  OWNER TO postgres;


-- Table: entity.vehicle

-- DROP TABLE entity.vehicle;

CREATE TABLE entity.vehicle
(
  mark character varying(150),
  model character varying(150),
  creation_year integer,
  plaque character varying(10),
  id serial NOT NULL,
  user_id integer,
  CONSTRAINT pk_vehicle PRIMARY KEY (id),
  CONSTRAINT fk_user_id FOREIGN KEY (id)
      REFERENCES entity."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entity.vehicle
  OWNER TO postgres;
