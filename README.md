Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote on which restaurant they want to have lunch at
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we asume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.



#### credential
`user_1@yandex.ru	a123321	ROLE_USER;`
`user_2@yandex.ru	b123321	ROLE_USER;`
`admin@mail.ru	a123321	ROLE_ADMIN;`


#### get user 
`curl -s  http://localhost:8080/rest/admin/user/2 --user user_2@yandex.ru:b123321`
#### add new user
`curl -s -X POST -d ' { "name": "mikeNew", "email": "mike@yandex.ru", "role": "ROLE_ADMIN", "password": "123321" }' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/user --user admin@mail.ru:a123321`
#### delete user
`curl -s -X DELETE http://localhost:8080/rest/admin/user/3 --user admin@mail.ru:a123321`

#### get restaurant
`curl -s http://localhost:8080/rest/admin/restaurant/1 --user user_1@yandex.ru:a123321
`
#### add new restaurant
`curl -s -X POST -d ' { "name": "BurgerKing"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurant --user admin@mail.ru:a123321
`
#### delete restaurant 
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurant/1 --user admin@mail.ru:a123321`

#### get menu
`curl -s http://localhost:8080/rest/admin/restaurants/1/menus/1 --user user_1@yandex.ru:a123321`
#### update menu

`curl -s -X PUT -d ' {"id":3,"name":"BigMacUPDATENEW!!!","price":444,"localDate":"2019-08-16"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/1/menus/update --user admin@mail.ru:a123321`

#### delete menu

`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/1/menus/2 --user admin@mail.ru:a123321`

#### create vote or edit vote
`curl -s -X POST -H  'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/profile/restaurants/1/votes --user user_1@yandex.ru:a123321`

#### delete vote
`curl -s -X DELETE http://localhost:8080/rest/admin/vote/1 --user user_2@yandex.ru:b123321`

