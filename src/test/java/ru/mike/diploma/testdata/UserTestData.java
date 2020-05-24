package ru.mike.diploma.testdata;

import ru.mike.diploma.model.Role;
import ru.mike.diploma.model.User;

import static ru.mike.diploma.model.Role.ROLE_ADMIN;

public class UserTestData {
    public static final int USER_1_ID = 1;
    public static final int USER_2_ID = 2;
    public static final int ADMIN_ID = 3;

    public static final User USER_1 = new User(USER_1_ID,"USER_1",  "user_1@yandex.ru", Role.ROLE_USER, "a123321");
    public static final User USER_2 = new User( USER_2_ID,"USER_2", "user_2@yandex.ru", Role.ROLE_USER, "b123321");
    public static final User ADMIN = new User(ADMIN_ID,"ADMIN",  "admin@mail.ru", ROLE_ADMIN, "a123321");

    public static User getCreatedUser() {
        return new User("new user", "newuser@mail.ru",Role.ROLE_USER,"password");
    }

    public static User getUpdatedUser() {
        return new User( USER_1_ID,"updated user", "user_1@yandex.ru", Role.ROLE_USER, "password");
    }
}
