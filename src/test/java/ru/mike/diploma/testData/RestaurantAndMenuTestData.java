package ru.mike.diploma.testData;

import ru.mike.diploma.model.Menu;
import ru.mike.diploma.model.Restaurant;

import java.time.LocalDate;
import java.util.List;


public class RestaurantAndMenuTestData {

    public static final int REST1_ID = 1;
    public static final int REST2_ID = 2;
    public static final int REST3_ID = 3;
    public static final Restaurant MACD = new Restaurant(REST1_ID, "MacDonalds");
    public static final Restaurant KFC = new Restaurant(REST2_ID, "KFC");
    public static final Restaurant DoDo = new Restaurant(REST3_ID, "DoDo");
    public static final int VOTES_1 = 3;
    public static final int VOTES_2 = 1;
    public static final LocalDate DATE_1 = LocalDate.of(2019, 8, 16);
    public static final LocalDate DATE_2 = LocalDate.now();

    public static final Menu MENU1_OF_REST_1 = new Menu("BigMac", 1, 444, MACD, DATE_1);
    public static final Menu MENU2_OF_REST_1 = new Menu("CheeseBurger", 2, 1400, MACD, DATE_1);
    public static final Menu MENU3_OF_REST_1 = new Menu("BigMac2", 5, 555, MACD, DATE_2);
    public static final Menu MENU1_OF_REST_2 = new Menu("Twister", 4, 232, KFC, DATE_1);
    public static final Menu MENU2_OF_REST_2 = new Menu("Fries", 3, 1400, KFC, DATE_2);

    public static final List<Menu> MENUS_OF_REST_1 = List.of(MENU3_OF_REST_1,
            MENU1_OF_REST_1, MENU2_OF_REST_1);

    public static final List<Menu> TODAYS_MENUS_OF_REST_1 = List.of(MENU3_OF_REST_1);

    public static final List<Menu> TODAYS_MENUS_OF_REST_2 = List.of(MENU2_OF_REST_2);

    public static Menu getNewMenu() {
        return new Menu("NewBigMac", 2222, KFC, LocalDate.now().plusDays(1));
    }
    public static Restaurant updateRestaurant(){
      //  MACD.setName("NewMacdonalds");
        //return MACD;
        return new Restaurant(REST1_ID,"NewMacd");
    }

}
