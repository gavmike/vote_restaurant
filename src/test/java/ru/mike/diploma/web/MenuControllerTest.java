package ru.mike.diploma.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.services.MenuService;
import ru.mike.diploma.web.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mike.diploma.testData.RestaurantAndMenuTestData.*;
import static ru.mike.diploma.testData.UserTestData.*;
import static ru.mike.diploma.web.UtilWebTest.contentJson;
import static ru.mike.diploma.web.UtilWebTest.userHttpBasic;

public class MenuControllerTest extends AbstractControllerTest {
    @Autowired
    MenuService menuService;
    public static final String URL = MenuController.URL + "/";

    @Test
    public void get() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders.get(URL + MENU_1_ID, REST1_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1_OF_REST_1));
        Menu returnMenu = UtilWebTest.readFromJson(resultActions, Menu.class);
        assertThat(menuService.get(MENU_1_ID, REST1_ID)).isEqualTo(returnMenu);

    }

    @Test
    public void getAllByRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "all", REST2_ID)
                .with(userHttpBasic(USER_2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1_OF_REST_2, MENU2_OF_REST_2));
    }

    @Test
    public void create() throws Exception {
        Menu newMenu = getNewMenu();
        ResultActions resultActions = perform(MockMvcRequestBuilders.post(URL, REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated())
                .andDo(print());
        Menu returnedMenu = UtilWebTest.readFromJson(resultActions, Menu.class);
        assertThat(menuService.get(returnedMenu.getId(), REST1_ID)).isEqualTo(returnedMenu);
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + MENU_1_ID, REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThat(menuService.getAllByRestaurantId(REST1_ID)).isEqualTo(List.of(MENU2_OF_REST_1, MENU3_OF_REST_1));
    }

    @Test
    public void update() throws Exception {
        Menu updatedMenu = getUpdatedMenu();
        perform(MockMvcRequestBuilders.put(URL + MENU_1_ID, REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updatedMenu)))
                .andExpect(status().isCreated());
        assertThat(menuService.get(MENU_1_ID, REST1_ID)).isEqualTo(updatedMenu);

    }
}