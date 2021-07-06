DROP TABLE IF EXISTS device;

CREATE TABLE device
(
    id            varchar(36) PRIMARY KEY,
    status        VARCHAR(50) NOT NULL,
    temperature   VARCHAR(10) NOT NULL,
    created_by    VARCHAR(100) NOT NULL,
    created_date  DATE NOT NULL,
    modified_by   VARCHAR(100),
    modified_date DATE
);

ALTER TABLE device ADD CONSTRAINT device_id_UNIQUE   UNIQUE ( id);

DROP TABLE IF EXISTS sim;

CREATE TABLE sim
(
    id             varchar(36) PRIMARY KEY,
    operator_code VARCHAR(40),
    country       VARCHAR(10),
    status        VARCHAR(50),
    created_by    VARCHAR(100),
    created_date  DATE,
    modified_by   VARCHAR(100),
    modified_date DATE
);

ALTER TABLE sim ADD CONSTRAINT sim_id_UNIQUE   UNIQUE ( id);


DROP TABLE IF EXISTS device_sim_mapping;

CREATE TABLE device_sim_mapping
(
    id            varchar(36) PRIMARY KEY,
    device_id      varchar(36),
    sim_id         varchar(36),
    created_by    VARCHAR(100),
    created_date   DATE,
    modified_by   VARCHAR(100),
    modified_date DATE
);

ALTER TABLE device_sim_mapping ADD CONSTRAINT device_sim_mapping_id   UNIQUE ( device_id, sim_id );