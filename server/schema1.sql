create database nusproject;

use nusproject;

create table _user (
	user_id			int not null auto_increment,
	first_name		varchar(255) not null,
    last_name		varchar(255) not null,
    email			varchar(255) not null,
    password		varchar(255) not null,
    role			varchar(255) not null,
	primary key(userId)
);

select * from _user;

