create database nusproject;

use nusproject;

create table _user(
user_id			int not null,
created_date	date not null,
email			varchar(255) not null,
first_name		varchar(255) not null,
last_name		varchar(255) not null,
password		varchar(255) not null,
role			varchar(255) not null,
primary key (user_id)
);