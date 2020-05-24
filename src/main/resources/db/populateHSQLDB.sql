delete
from RESTAURANT;
delete
from VOTE;
delete
from MENU;
delete
from USER;


ALTER TABLE RESTAURANT
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE MENU
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE USER
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE VOTE
    ALTER COLUMN id RESTART WITH 1;


insert into restaurant (name)
values ('MacDonalds');
insert into restaurant (name)
values ('KFC');

insert into menu(name, price, date, id_rest)
values ('BigMac', 444, '2019-08-17', 1),
       ('CheeseBurger', 1400, '2020-05-17', 1),
       ('Fries', 1400, current_date, 2),
       ('Twister', 232, '2019-08-17', 2),
       ('BigMac2', 555, current_date, 1);



insert into user(name, email, password, role)
values ('USER_1', 'user_1@yandex.ru', 'a123321', 'ROLE_USER');
insert into user(name, email, password, role)
values ('USER_2', 'user_2@yandex.ru', 'b123321', 'ROLE_USER');
insert into user(name, email, password, role)
values ('ADMIN', 'admin@mail.ru', 'a123321', 'ROLE_ADMIN');



insert into vote (date, id_rest, id_user)
VALUES ('2019-10-12', 1, 2),
       ('2019-10-12', 2, 1),
       ('2019-11-12', 2, 3);

