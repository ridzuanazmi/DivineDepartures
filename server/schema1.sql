create database tfipProject;

use tfipproject;

create table _user(
user_id			int not null auto_increment,
created_date	date not null,
email			varchar(255) unique,
full_name		varchar(255) not null,
phone_number	varchar(32) not null,
password		varchar(255) not null,
role			varchar(12) not null,
primary key (user_id)
);

create table contact_message(
contact_id		varchar(16) not null,
contact_email	varchar(255),
contact_name	varchar(255),
created_date	varchar(128),
message			varchar(500),
subject			varchar(50),
primary key (contact_id)
);

create table shop(
id					int not null auto_increment,
block				varchar(12),
curved_mosaic_tile	varchar(12),
date_of_death		date,
deceased_name		varchar(255),
plant				varchar(128),
plot_number			int,
tiles				varchar(32),
tombstone_height	varchar(32),
tombstone_material	varchar(32),
top_cover			varchar(32),
user_id				int,
primary key (id),
foreign key (user_id) references _user(user_id) on delete cascade
);

select * from _user;
select * from contact_message;
select * from shop;

UPDATE _user SET full_name = "test", email = "test@mail.com", phone_number = "89658965", role = "USER" WHERE user_id = 8;
DELETE from _user where user_id = 3;