package ru.mike.diploma.persistence.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.persistence.MenuJPA;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Component
public class MenuJPAJDBCTemplateImpl implements MenuJPA {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restID) throws SQLException {
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        return jdbcTemplate.query("select * from  menu where id_rest = ? and datemenu = ? "
                , new Object[]{restID, localDate}, new BeanPropertyRowMapper<>(Menu.class));
    }



    @Override
    public Menu getMenu(int menuID) {

        return (Menu) jdbcTemplate.query("select  * from menu where id =?",new Object[] {menuID},new MenuMapper()).stream().findAny().orElse(null);

    }

    @Override
    public void deleteMenu(int menuID) {
        jdbcTemplate.update("delete from menu where id=?", menuID);

    }

    @Override
    public void addMenu(Menu menu) throws Exception {
        jdbcTemplate.update("insert into menu(name, price, datemenu, id_rest)  value (?,?,?,?)", menu.getName()
                , menu.getPrice(), menu.getLocalDate(), menu.getRestaurant().getId());

    }

    @Override
    public List<Menu> getAllMenuDate(LocalDate localDate) {
        TimeZone.setDefault(TimeZone.getTimeZone("Eupope/Moscow"));
        return jdbcTemplate.query("select * from  menu where datemenu = ?", new Object[]{localDate}, new MenuMapper());

    }

    @Override
    public void updateMenu(Menu menu, int restID) {
        jdbcTemplate.update("update menu set name=?, price=?, datemenu=?  where id_rest =? and id = ?  ", menu.getName(), menu.getPrice()
                , menu.getLocalDate(), menu.getId(), restID);

    }
}
