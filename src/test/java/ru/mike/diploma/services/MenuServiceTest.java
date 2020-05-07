package ru.mike.diploma.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static ru.mike.diploma.testData.RestaurantAndMenuTestData.*;

public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    MenuService menuService;

    @Test
    public void getById() {
        Menu menu = menuService.get(1, 1);
        Assert.assertEquals(MENU1_OF_REST_1, menu);
    }

    @Test
    public void addMenu() {
        Menu newMenu = getNewMenu();
        menuService.add(newMenu, 2);
        Assert.assertEquals(newMenu, menuService.get(6, 2));
    }

    @Test
    public void deleteMenu() {
        LocalDate localDate = menuService.get(5, 1).getLocalDate();
        menuService.delete(5, 1);
        Assert.assertEquals(menuService.getAllByRestaurantId(1), List.of(MENU1_OF_REST_1, MENU2_OF_REST_1));
    }

    @Test
    public void getAllMenuDateandRestID() {

        List<Menu> menuListDB = menuService.getAllByDateAndRestaurantId(DATE_1, REST1_ID);
        Assert.assertEquals(List.of(MENU1_OF_REST_1, MENU2_OF_REST_1), menuListDB);
    }

    @Test
    public void getAllMenu() {
        List<Menu> menuListDB = menuService.getAllByRestaurantId(1);
        Assert.assertEquals(List.of(MENU1_OF_REST_1, MENU2_OF_REST_1, MENU3_OF_REST_1), menuListDB);
    }
}
