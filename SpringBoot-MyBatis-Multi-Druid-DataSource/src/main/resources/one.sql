DROP TABLE IF EXISTS `one`.`address`;
DROP DATABASE IF EXISTS `one`;

CREATE DATABASE one;
CREATE TABLE one.address
(
    id        bigint       not null primary key,
    city      varchar(255) null,
    state     varchar(255) null,
    street    varchar(255) null,
    zip_code  varchar(255) null,
    person_id bigint       null
);

INSERT INTO one.address (id, city, state, street, zip_code, person_id)
VALUES (1, 'Los Angeles', 'CA', 'Stanford Ave', '90001', 1);
INSERT INTO one.address (id, city, state, street, zip_code, person_id)
VALUES (2, 'Abington', 'MA', 'Brockton Avenue', '02305', 2);