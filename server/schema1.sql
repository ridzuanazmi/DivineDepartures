create database tfipProject;

use tfipproject;

create table _user(
user_id			int not null auto_increment,
created_date	date not null,
email			varchar(255) unique,
full_name		varchar(255) not null,
phone_number	varchar(32) not null,
password		varchar(255) not null,
role			varchar(128) not null,
primary key (user_id)
);

create table contact_message(
contact_id		varchar(128) not null,
contact_email	varchar(255),
contact_name	varchar(255),
created_date	varchar(255),
message			varchar(500),
subject			varchar(255),
primary key (contact_id)
);
select * from _user;
select * from contact_message;
select * from shop;

UPDATE _user SET full_name = ?, email = ?, phone_number = ? WHERE id = 4