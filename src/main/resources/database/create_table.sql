create table task
(
    description varchar(50) charset utf8 null,
    status      tinyint(1) default 0     null,
    id          int auto_increment
        primary key
);
