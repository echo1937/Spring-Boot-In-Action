CREATE TABLE user_table
(
    id          bigserial Primary Key,
    nickname    varchar(255) NOT NULL,
    sex         smallint     NOT NULL,
    hobbies     jsonb        NOT NULL,
    ipaddress   inet         NOT NULL default '127.0.0.1',
    create_time timestamp    NOT NULL,
    update_time timestamp    NOT NULL,
    delete_time timestamp
);

comment on column user_table.nickname is '昵称';
comment on column user_table.sex is '性别，1男2女';
comment on column user_table.hobbies is '爱好';
comment on column user_table.create_time is '创建时间';
comment on column user_table.update_time is '更新时间';
comment on column user_table.delete_time is '删除时间';