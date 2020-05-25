package ru.mike.diploma.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mike.diploma.model.User;
import ru.mike.diploma.services.UserService;
import ru.mike.diploma.web.json.JsonUtil;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mike.diploma.testdata.UserTestData.*;
import static ru.mike.diploma.web.UtilWebTest.contentJson;
import static ru.mike.diploma.web.UtilWebTest.userHttpBasic;
import static org.assertj.core.api.Assertions.assertThat;

public class AdminUserControllerTest extends AbstractControllerTest {
    @Autowired
    UserService userService;
    public static final String URL = AdminUserController.URL + '/';

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + USER_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER_1));
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + USER_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThat(userService.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(List.of(USER_2, ADMIN));
    }

    @Test
    public void update() throws Exception {
        User updateUser = getUpdatedUser();
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updateUser)))
                .andExpect(status().isCreated());

assertThat(userService.get(updateUser.getId())).isEqualToIgnoringGivenFields(updateUser,"votes","password");

    }

    @Test
    public void add() throws Exception {
        User createdUser = getCreatedUser();
        ResultActions resultActions = perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(createdUser)))
                .andExpect(status().isCreated());

        User returnUser = UtilWebTest.readFromJson(resultActions,User.class);
        createdUser.setId(returnUser.getId());
        assertThat(userService.get(returnUser.getId())).isEqualToIgnoringGivenFields(createdUser,"votes","password");
    }
}