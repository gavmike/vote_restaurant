package ru.mike.diploma.persistence.jdbctemplate;

import org.springframework.jdbc.core.RowMapper;
import ru.mike.diploma.model.Menu;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements RowMapper {
    @Override
    public Menu mapRow(ResultSet resultSet, int i) throws SQLException {

        String name = resultSet.getString("name");
        Integer id = resultSet.getInt("id");
        Integer price = resultSet.getInt("price");
        Date date = resultSet.getDate("datemenu");
        Menu menu = new Menu(id,name,  price, date.toLocalDate());
        return menu;
    }
}
