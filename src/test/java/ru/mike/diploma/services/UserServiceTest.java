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
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(List.of(mike, dive, chak, newUser));

    }

    @Test
    public void delete() {
        userService.delete(mike_ID);
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes", "password").isEqualTo(List.of(dive, chak));

    }

    @Test
    public void get() {
        User user = userService.get(mike_ID);
        assertThat(user).isEqualToIgnoringGivenFields(mike, "votes", "password");
    }

    @Test
    public void getByEmail() {
        User user = userService.getByEmail("mike@yandex.ru");
        assertThat(user).isEqualToIgnoringGivenFields(mike, "votes", "password");
    }


}