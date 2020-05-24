package ru.mike.diploma.persistence.todo.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.persistence.todo.MenuJpa;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Component
public class MenuJpaJdbcTemplate implements MenuJpa {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restId) throws SQLException {
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        return jdbcTemplate.query("select * from  menu where id_rest = ? and date = ? "
                , new Object[]{restId, localDate}, new BeanPropertyRowMapper<>(Menu.class));
    }

    @Override
    public Menu getMenu(int menuId) {

        return (Menu) jdbcTemplate.query("select  * from menu where id =?",new Object[] {menuId},new MenuMapper()).stream().findAny().orElse(null);
    }

    @Override
    public void deleteMenu(int menuId) {
        jdbcTemplate.update("delete from menu where id=?", menuId);

    }

    @Override
    public void addMenu(Menu menu) throws Exception {
        jdbcTemplate.update("insert into menu(name, price, date, id_rest)  value (?,?,?,?)", menu.getName()
                , menu.getPrice(), menu.getLocalDate(), menu.getRestaurant().getId());
    }

    @Override
    public List<Menu> getAllMenuDate(LocalDate localDate) {
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        return jdbcTemplate.query("select * from  menu where date = ?", new Object[]{localDate}, new MenuMapper());

    }

    @Override
    public void updateMenu(Menu menu, int restId) {
        jdbcTemplate.update("update menu set name=?, price=?, date=?  where id_rest =? and id = ?  ", menu.getName(), menu.getPrice()
                , menu.getLocalDate(), menu.getId(), restId);

    }
}
