create database nusproject;

use nusproject;

create table _user(
user_id			int not null auto_increment,
created_date	date not null,
email			varchar(255) unique,
first_name		varchar(255) not null,
last_name		varchar(255) not null,
password		varchar(255) not null,
role			varchar(255) not null,
primary key (user_id)
);

select * from _user;

delete from _user where user_id=52;

create table contact_message(
contact_id		varchar(255) not null,
contact_email	varchar(255),
contact_name	varchar(255),
created_date	varchar(255),
message			varchar(255),
subject			varchar(255),
primary key (contact_id)
);