package ru.mike.diploma.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mike.diploma.model.User;
import ru.mike.diploma.testData.UserTestData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mike.diploma.testData.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void add() {
        User newUser = UserTestData.getCreatedUser();
        userService.add(newUser);
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(List.of(USER_1, USER_2, ADMIN, newUser));

    }

    @Test
    public void delete() {
        userService.delete(USER_1_ID);
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(List.of(USER_2, ADMIN));

    }

    @Test
    public void get() {
        User user = userService.get(USER_1_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes", "password");
    }

    @Test
    public void getByEmail() {
        User user = userService.getByEmail("user_1@yandex.ru");
        assertThat(user).isEqualToIgnoringGivenFields(USER_1, "votes", "password");
    }


}