CREATE TABLE `user_table`
(
    `id`          bigint Primary Key AUTO_INCREMENT,
    `nickname`    varchar(255) NOT NULL COMMENT '昵称',
    `sex`         tinyint(1)   NOT NULL COMMENT '性别，1男2女',
    `hobbies`     json         NOT NULL COMMENT '爱好',
    `ipaddress`   varchar(43)  NOT NULL DEFAULT '127.0.0.1' COMMENT 'IP地址',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `deleted`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否删除，1是0否'
) ENGINE = InnoDB
  AUTO_INCREMENT = 50
  DEFAULT CHARSET = utf8mb4;