-- auto-generated definition
create table t_data_source_config
(
    id                bigint auto_increment comment '自增主键'
        primary key,
    driver_class_name varchar(50)  not null comment '数据库驱动全限定类名',
    jdbc_url          varchar(500) not null comment '数据库连接字符串',
    username          varchar(255) not null comment '数据库连接帐号',
    password          varchar(255) not null comment '数据库连接密码',
    system_code       varchar(50)  not null comment '系统编码',
    constraint t_data_source_config_system_code_uindex
        unique (system_code)
)
    comment '数据源配置信息表';

