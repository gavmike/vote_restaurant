package ru.mike.diploma.testData;

import ru.mike.diploma.model.Role;
import ru.mike.diploma.model.User;

import static ru.mike.diploma.model.Role.ROLE_ADMIN;

public class UserTestData {
    public static final int USER_1_ID = 1;
    public static final int USER_2_ID = 2;
    public static final int ADMIN_ID = 3;

    public static final User USER_1 = new User("USER_1", USER_1_ID, "user_1@yandex.ru", Role.ROLE_USER, "a123321");
    public static final User USER_2 = new User("USER_2", USER_2_ID, "user_2@yandex.ru", Role.ROLE_USER, "b123321");
    public static final User ADMIN = new User("ADMIN", ADMIN_ID, "admin@mail.ru", ROLE_ADMIN, "a123321");

    public static User getCreatedUser() {
        return new User("new user", "newuser@mail.ru","password",Role.ROLE_USER );
    }

    public static User getUpdatedUser() {
        return new User("updated user", USER_1_ID, "user_1@yandex.ru", Role.ROLE_USER, "password");
    }
}
