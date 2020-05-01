package ru.mike.diploma.services;

import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu addMenu(Menu menu, int rest_id);

    List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restID);


    List<Menu> getAllMenu(int restID);

    Menu getMenu(int menuID, int restID);

    void deleteMenu(int menuID, int restID);
}
