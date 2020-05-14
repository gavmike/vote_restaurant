package ru.mike.diploma;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.services.MenuService;
import ru.mike.diploma.services.RestaurantService;
import ru.mike.diploma.services.UserService;


import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-web.xml");
        RestaurantService restaurantService = (RestaurantService) applicationContext.getBean("restaurantServiceImpl");
        System.out.println(restaurantService.getAllWithTodayMenu(LocalDate.now()));

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-web.xml")) {
            appCtx.getEnvironment().setActiveProfiles("hsqldb","datajpa");
            appCtx.refresh();
        }



    }
}
