DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `person`;

create table if not exists address
(
    id        bigint       not null primary key,
    city      varchar(255) null,
    state     varchar(255) null,
    street    varchar(255) null,
    zip_code  varchar(255) null,
    person_id bigint       null
);

create table if not exists person
(
    id         bigint       not null primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

