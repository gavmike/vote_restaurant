
delete from restaurant;
delete from vote;
delete from menu;
delete from user;

ALTER TABLE RESTAURANT ALTER COLUMN id RESTART WITH 1;
ALTER TABLE MENU ALTER COLUMN id RESTART WITH 1;
ALTER TABLE USER ALTER COLUMN id RESTART WITH 1;
ALTER TABLE VOTE ALTER COLUMN id RESTART WITH 1;

insert into restaurant ( name) values
('MacDonalds');
insert into restaurant ( name) values
( 'KFC');


insert into menu(name, price, datemenu, id_rest) values
('BigMac', 444, '2019-08-16', 1),
( 'CheeseBurger', 1400, '2019-08-16', 1),
('Fries', 1400, current_date, 2),
( 'Twister', 232, '2019-08-16', 2),
('BigMac2', 555, current_date, 1);


insert into user(name, email, password, role) values
('mike', 'mike@yandex.ru', 'a123321', 'ROLE_USER');
insert into user(name, email, password, role) values
('dive', 'dive@yandex.ru', 'b123321', 'ROLE_USER');
insert into user(name, email, password, role) values
('chak', 'chak@mail.ru', 'a123321', 'ROLE_ADMIN');

insert into vote (dateVote, id_rest, id_user) VALUES
('2019-10-12',1,2),
('2019-10-12',2,1),
('2019-11-12',2,3);

