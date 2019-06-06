show databases;

create database catchmind;

use catchmind;

create table user(
id varchar(20) not null,
password varchar(20) not null
);

insert into user(id, password) values('admin', '1234');
select * from user;