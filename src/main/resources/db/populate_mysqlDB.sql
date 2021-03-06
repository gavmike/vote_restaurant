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


insert into menu(name, price, date, id_rest) value
    ('BigMac', 444, '2019-8-17', 1),
    ( 'CheeseBurger', 1400, '2020-05-17', 1),
    ('Fries', 1400, current_date, 2),
    ( 'Twister', 232, '2019-8-17', 2),
    ('BigMac2', 555, current_date, 1);


insert into user(name, email, password, role) value
    ('USER_1', 'user_1@yandex.ru', 'a123321', 'ROLE_USER');
insert into user(name, email, password, role) value
    ('USER_2', 'user_2@yandex.ru', 'b123321', 'ROLE_USER');
insert into user(name, email, password, role) value
    ('ADMIN', 'admin@mail.ru', 'a123321', 'ROLE_ADMIN');

insert into vote (date, id_rest, id_user) VALUE
('2019-10-12',1,2),
('2019-10-12',2,1),
('2019-11-12',2,3);