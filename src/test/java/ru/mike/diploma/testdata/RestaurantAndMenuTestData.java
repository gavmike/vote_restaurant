package ru.mike.diploma.testdata;

import ru.mike.diploma.model.Menu;
import ru.mike.diploma.model.Restaurant;

import java.time.LocalDate;

public class RestaurantAndMenuTestData {
    public static final int REST1_ID = 1;
    public static final int REST2_ID = 2;
    public static final int REST3_ID = 3;
    public static final int MENU_1_ID = 1;
    public static final int MENU_2_ID = 2;
    public static final int MENU_3_ID = 3;
    public static final int MENU_4_ID = 4;
    public static final int MENU_5_ID = 5;
    public static final Restaurant MACD = new Restaurant(REST1_ID, "MacDonalds");
    public static final Restaurant KFC = new Restaurant(REST2_ID, "KFC");
    public static final Restaurant DoDo = new Restaurant(REST3_ID, "DoDo");
    public static final int VOTES_1 = 3;
    public static final int VOTES_2 = 1;
    public static final LocalDate DATE_1 = LocalDate.of(2019, 8, 17);
    public static final LocalDate DATE_2 = LocalDate.now();
    public static final LocalDate DATE_3 = LocalDate.of(2020, 5, 17);

    public static final Menu MENU1_OF_REST_1 = new Menu(1,"BigMac",  444, MACD, DATE_1);
    public static final Menu MENU2_OF_REST_1 = new Menu(2,"CheeseBurger",  1400, MACD, DATE_3);
    public static final Menu MENU3_OF_REST_1 = new Menu(5,"BigMac2",  555, MACD, DATE_2);
    public static final Menu MENU1_OF_REST_2 = new Menu(4,"Twister",  232, KFC, DATE_1);
    public static final Menu MENU2_OF_REST_2 = new Menu(3,"Fries",  1400, KFC, DATE_2);

    public static Menu getNewMenu() {
        return new Menu("NewBigMac", 2222, KFC, DATE_3);
    }

    public static Menu getUpdatedMenu() {
        return new Menu(MENU_1_ID, "Updated Mac", 3333, KFC, DATE_1);
    }

    public static Restaurant updateRestaurant() {
        return new Restaurant(REST1_ID, "NewMacd");
    }

}
