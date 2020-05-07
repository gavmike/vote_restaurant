package ru.mike.diploma.testData;

import ru.mike.diploma.model.Role;
import ru.mike.diploma.model.User;

import static ru.mike.diploma.model.Role.ROLE_ADMIN;

public class UserTestData {
    public static final int mike_ID = 1;
    public static final int dive_ID = 2;
    public static final int chak_ID = 3;

    public static final User mike = new User("mike", mike_ID, "mike@yandex.ru", Role.ROLE_USER, "a123321");
    public static final User dive = new User("dive", dive_ID, "dive@yandex.ru", Role.ROLE_USER, "b123321");
    public static final User chak = new User("chak", chak_ID, "chak@mail.ru", ROLE_ADMIN, "a123321");

    public static User getCreatedUser() {
        return new User("new user", "newuser@mail.ru","password",Role.ROLE_USER );
    }

    public static User getUpdatedUser() {
        return new User("updated user", mike_ID, "user1@yandex.ru", Role.ROLE_USER, "password");
    }
}
