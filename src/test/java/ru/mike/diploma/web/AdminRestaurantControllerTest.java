package ru.mike.diploma.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.services.RestaurantService;
import ru.mike.diploma.testData.RestaurantAndMenuTestData;
import ru.mike.diploma.web.json.JsonUtil;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mike.diploma.testData.RestaurantAndMenuTestData.*;
import static ru.mike.diploma.testData.UserTestData.ADMIN;
import static ru.mike.diploma.web.UtilWebTest.contentJson;
import static ru.mike.diploma.web.UtilWebTest.userHttpBasic;
import static ru.mike.diploma.web.json.JsonUtil.writeValue;

public class AdminRestaurantControllerTest extends AbstractControllerTest {
    @Autowired
    RestaurantService restaurantService;
    private static final String URL = AdminRestaurantController.URL + '/';

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MACD));
    }

    @Test
    public void add() throws Exception {
        ResultActions perform = perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(DoDo))
                .with(userHttpBasic(ADMIN)));
        Restaurant addRestaurant = UtilWebTest.readFromJson(perform, Restaurant.class);
        int newId = addRestaurant.getId();
        DoDo.setId(newId);
        assertThat(addRestaurant).isEqualTo(DoDo).isEqualToIgnoringGivenFields(DoDo, "menuList", "votes");
    }

    @Test
    public void update() throws Exception {
        Restaurant updateRestaurant = RestaurantAndMenuTestData.updateRestaurant();
               perform(MockMvcRequestBuilders.put(URL + REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updateRestaurant))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThat(restaurantService.get(REST1_ID)).isEqualToIgnoringGivenFields(updateRestaurant, "menuList", "votes");
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + REST2_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThat(restaurantService.getAll()).usingElementComparatorIgnoringFields("menuList", "votes").isEqualTo(List.of(MACD));
    }

    @Test
    public void getToday() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "getAllTodayMenu")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(List.of(MACD, KFC)));
    }
}