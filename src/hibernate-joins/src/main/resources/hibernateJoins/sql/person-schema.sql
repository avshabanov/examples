
-- Schema overview:
-- Person (e.g. Bob), Company (e.g. Starbucks), Position (e.g. Manager),
-- Employment (e.g. Bob worked at Starbucks as a Manager from 2012 to 2013)

CREATE TABLE person (
  id      INTEGER PRIMARY KEY,
  name    VARCHAR(256) NOT NULL,
  age     INTEGER NOT NULL,
  CONSTRAINT uq_person_name UNIQUE (name)
);

CREATE TABLE company (
  id          INTEGER PRIMARY KEY,
  name        VARCHAR(256) NOT NULL,
  stock_sym   CHAR(8),
  CONSTRAINT uq_company_name UNIQUE (name),
  CONSTRAINT uq_company_stock_sym UNIQUE (stock_sym)
);

CREATE TABLE company_position (
  id          INTEGER PRIMARY KEY,
  name        VARCHAR(256) NOT NULL,
  CONSTRAINT uq_company_position_name UNIQUE (name)
);

CREATE TABLE employment_entry (
  id          INTEGER PRIMARY KEY,
  start_date  DATE NOT NULL,
  end_date    DATE, -- NULL for active positions
  person_id   INTEGER NOT NULL,
  company_id  INTEGER NOT NULL,
  pos_id      INTEGER NOT NULL,
  CONSTRAINT fk_employment_entry_person FOREIGN KEY (person_id) REFERENCES person(id),
  CONSTRAINT fk_employment_entry_company FOREIGN KEY (company_id) REFERENCES company(id),
  CONSTRAINT fk_employment_entry_position FOREIGN KEY (pos_id) REFERENCES company_position(id)
);

CREATE SEQUENCE seq_person                START WITH 100;
CREATE SEQUENCE seq_company               START WITH 300;
CREATE SEQUENCE seq_company_position      START WITH 500;
CREATE SEQUENCE seq_employment_entry      START WITH 1000;
