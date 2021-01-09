DROP TABLE IF EXISTS `two`.`person`;
DROP DATABASE IF EXISTS `two`;

create database two;
create table two.person
(
    id         bigint       not null primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

INSERT INTO two.person (id, first_name, last_name)
VALUES (1, 'John', 'Doe');
INSERT INTO two.person (id, first_name, last_name)
VALUES (2, 'John', 'Lewis');