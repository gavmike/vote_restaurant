package ru.mike.diploma.persistence.trash;

import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository2 {

    Menu add(Menu menu);

    Menu get(int id);

    void delete(int id);

    List<Menu> getAllbyDateAndRestaurantId(LocalDate localDate, int restId);

    List<Menu> getAllbyRestaurantId(int restId);
}
