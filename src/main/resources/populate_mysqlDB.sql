use projectvoterestaurant;
delete from vote;
delete from menu;
delete from user;
delete from restaurant;
ALTER TABLE restaurant AUTO_INCREMENT = 1;
ALTER TABLE menu AUTO_INCREMENT = 1;
ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE vote AUTO_INCREMENT = 1;


insert into restaurant ( name) value
    ('MacDonalds');
insert into restaurant ( name) value
    ( 'KFC');


insert into menu(name, price, datemenu, id_rest) value
    ('BigMac', 444, '2019-8-16', 1),
    ( 'CheeseBurger', 1400, '2019-8-16', 1),
    ('Fries', 1400, current_date, 2),
    ( 'Twister', 232, '2019-8-16', 2),
    ('BigMac2', 555, current_date, 1);


insert into user(name, email, password, role) value
    ('mike', 'mike@yandex.ru', 'a123321', 'ROLE_USER');
insert into user(name, email, password, role) value
    ('dive', 'dive@yandex.ru', 'b123321', 'ROLE_USER');
insert into user(name, email, password, role) value
    ('chak', 'chak@mail.ru', 'a123321', 'ROLE_ADMIN');

insert into vote (dateVote, id_rest, id_user) VALUE
('2019-10-12',1,2),
('2019-10-12',2,1),
('2019-11-12',2,3);