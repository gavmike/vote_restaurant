package ru.mike.diploma;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.services.MenuService;
import ru.mike.diploma.services.UserService;


import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-web.xml");
        RestaurantService restaurantService = (RestaurantService) applicationContext.getBean("restaurantServiceImpl");
        System.out.println(restaurantService.getAllwithTodayMenu(LocalDate.now()));*/

     /*   try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-web.xml")) {
            appCtx.getEnvironment().setActiveProfiles("hsqldb","datajpa");
            appCtx.refresh();
        }*/

        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles("hsqldb", "datajpa");
            appCtx.load("spring-web.xml");
            appCtx.refresh();
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
           MenuService menuService = (MenuService) appCtx.getBean("menuServiceImpl");
            UserService userService = (UserService)appCtx.getBean("userService");
            System.out.println( userService.getbyID(1));
           menuService.addMenu(new Menu("lunch223",333,LocalDate.now()),1);

         System.out.println(menuService.getAllMenu(1));
           // MenuJPA menuJPA = (MenuJPA) appCtx.getBean(MenuJPAJDBCTemplateImpl.class);
           // System.out.println( menuJPA.getMenu(1));

           /* Connection con = null;

            Statement stmt = null;
            ResultSet result = null;
            int res =0;

            try {
                //Registering the HSQLDB JDBC driver
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                //Creating the connection with HSQLDB
                con = DriverManager.getConnection("jdbc:hsqldb:file:db-data/mydatabase", "SA", "");
                if (con!= null){
                    System.out.println("Connection created successfully");
                    stmt = con.createStatement();
                    result = stmt.executeQuery("select * from menu");
                  *//*  while(result.next()){
                        System.out.println(result.getInt("id")+" | "+
                                result.getString("name"));
                    }*//*
                   // res = stmt.executeUpdate("CREATE TABLE tutorials_tbl (id INT NOT NULL, title VARCHAR(50) NOT NULL, author VARCHAR(20) NOT NULL, submission_date DATE, PRIMARY KEY (id)); ");
                   // System.out.println(res);


                }else{
                    System.out.println("Problem with creating connection");
                }

            }  catch (Exception e) {
                e.printStackTrace(System.out);
            }*/
           // con.prepareStatement("select * from menu");



           // System.out.println(menuJPA.getMenu(1));
           // System.out.println( menuJPA.getAllMenuDateandRestID(LocalDate.now(),1));
          //  menuJPA.addMenu(new Menu("lunch223",333, LocalDate.now()));

        }

    }
}
