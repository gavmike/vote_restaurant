package ru.mike.diploma.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mike.diploma.model.Restaurant;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.mike.diploma.testdata.RestaurantAndMenuTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    RestaurantService restaurantService;

    @Test
    public void get() {
        Restaurant rest = restaurantService.get(1);
        assertThat(rest).isEqualToIgnoringGivenFields(MACD, "votes", "menuList");
    }

    @Test
    public void delete() {
        restaurantService.delete(1);
        assertThat(restaurantService.getAll()).usingElementComparatorIgnoringFields("menuList","votes").isEqualTo(List.of(KFC));
    }

    @Test
    public void add() {
      restaurantService.add(DoDo);
        assertThat(restaurantService.getAll()).usingElementComparatorIgnoringFields("menuList","votes").isEqualTo(List.of(MACD,KFC,DoDo));

    }


    @Test
    public void getAll() {
        assertThat(restaurantService.getAll()).usingElementComparatorIgnoringFields("menuList","votes").isEqualTo(List.of(MACD,KFC));

    }

}
