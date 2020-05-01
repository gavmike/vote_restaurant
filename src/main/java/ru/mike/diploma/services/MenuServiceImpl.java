package ru.mike.diploma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.persistence.repository.MenuRepository;
import ru.mike.diploma.persistence.repository.RestaurantRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepositoryJPA;
    @Autowired
    private RestaurantRepository restaurantRepositoryJPA;




    public Menu addMenu(Menu menu, int rest_id) {
        Restaurant rest = restaurantRepositoryJPA.findById(rest_id).get();
        menu.setRestaurant(rest);
        menuRepositoryJPA.save(menu);
        return menu;
    }

    public List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restID) {
        return menuRepositoryJPA.getMenuByLocalDateAndRestaurantId(localDate, restID);
    }

    public Menu getMenu(int menuID, int restID) {

        return menuRepositoryJPA.getMenuByIdAndRestaurantId(menuID, restID);
    }

    public void deleteMenu(int menuID, int restID) {
        menuRepositoryJPA.deleteByIdAndAndRestaurantId(menuID, restID);
    }

    public List<Menu> getAllMenu(int restID) {
        return menuRepositoryJPA.getAllMenuByRestaurantId(restID);
    }
}
