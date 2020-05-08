use  projectvoterestaurant;
drop table  IF EXISTS vote;
drop table  if exists menu ;
drop table  IF EXISTS user;
drop table  if exists restaurant;


create table restaurant
(
    id int primary key auto_increment,
    name varchar(50)
);

create table menu
(
    id int primary key auto_increment,
    name varchar(50),
    price int,
    datemenu date,
    id_rest int, foreign key (id_rest) references restaurant(id) on delete cascade
);
create table user
(
    id int primary key auto_increment,
    name varchar(50),
    email varchar(50),
    password varchar(50),
    role varchar(50)
);
create unique index user_unique_email_idx
    on user (email);
create table vote
(
    id       int primary key auto_increment,
    dateVote date,
    id_rest  int,
    id_user  int,
    foreign key (id_rest) references restaurant (id) on delete cascade,
    foreign key (id_user) references user (id) on delete cascade
);
create unique index user_unique_dateVote_idx
    on vote (id_user, dateVote);