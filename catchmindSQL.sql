show databases;

create database catchmind;

use catchmind;
-- drop table user;
-- delete from user;

create table user(
id varchar(20) not null,
password varchar(20) not null,
nick varchar(20) not null,
score int not null
);

insert into user(id, password, nick, score) values('admin', '1234', "NPC",0);
insert into user(id, password) values('admin1', '1234');
insert into user(id, password) values('admin2', '1234');
select * from user;