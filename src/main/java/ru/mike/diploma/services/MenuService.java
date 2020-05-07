package ru.mike.diploma.services;

import javassist.NotFoundException;
import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu add(Menu menu, int restId);

    List<Menu> getAllByDateAndRestaurantId(LocalDate localDate, int restId);

    List<Menu> getAllByRestaurantId(int restId);

    Menu get(int menuId, int restId);

    void delete(int menuId, int restId) ;

}
