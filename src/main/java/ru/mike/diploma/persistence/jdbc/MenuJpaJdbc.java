package ru.mike.diploma.persistence.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.persistence.Menujpa;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

@Component
public class MenuJpaJdbc implements Menujpa {
    private static Connection conn;
    final static Logger LOG = LoggerFactory.getLogger(MenuJpaJdbc.class);

    static {
        String username = null;
        String password = null;
        String url = null;

        try {
            InputStream inputStream = new FileInputStream("src/main/resources/mysql.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restId) throws SQLException {

        List<Menu> menuList = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("select * from  menu where id_rest = ? and date = ?");
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        preparedStatement.setDate(2, Date.valueOf(localDate));
        preparedStatement.setInt(1, restId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            Integer id = resultSet.getInt("id");
            Integer price = resultSet.getInt("price");
            Date date = resultSet.getDate("datemenu");
            LOG.info("id={}", id);
            Menu menu = new Menu(id,name,  price, date.toLocalDate());
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public Menu getMenu(int menuId) {
        return null;
    }

    @Override
    public void deleteMenu(int menuId) throws SQLException {
        PreparedStatement prepSt = conn.prepareStatement("delete from menu where id=?");
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        prepSt.setInt(1, menuId);
        prepSt.execute();
    }

    @Override
    public void addMenu(Menu menu) throws Exception {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into menu(name, price, date, id_rest)  value (?,?,?,?)");
        preparedStatement.setString(1, menu.getName());
        preparedStatement.setLong(2, menu.getPrice());
        preparedStatement.setDate(3, Date.valueOf(menu.getLocalDate()));
        preparedStatement.setInt(4, menu.getRestaurant().getId());
        preparedStatement.execute();

    }

    @Override
    public List<Menu> getAllMenuDate(LocalDate localDate) throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        PreparedStatement prepst = conn.prepareStatement("select * from  menu where date = ?");
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        prepst.setDate(1, java.sql.Date.valueOf(localDate));
        ResultSet rs = prepst.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            Integer id = rs.getInt("id");
            Integer price = rs.getInt("price");
            Integer id_rest = rs.getInt("id_rest");
            Date datemenu = rs.getDate("datemenu");
            LOG.info("id={}", id);
            Menu menu = new Menu( id,name, price, datemenu.toLocalDate());
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public void updateMenu(Menu menu, int restId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("update menu set name=?, price=?, date=?  where id_rest =? and id = ?  ");
        preparedStatement.setString(1, menu.getName());
        preparedStatement.setLong(2, menu.getPrice());
        preparedStatement.setDate(3, Date.valueOf(menu.getLocalDate()));
        preparedStatement.setInt(4, restId);
        preparedStatement.setInt(5, menu.getId());
        preparedStatement.execute();
    }
}
