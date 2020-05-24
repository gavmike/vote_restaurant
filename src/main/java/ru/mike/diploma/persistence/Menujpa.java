package ru.mike.diploma.persistence;

import ru.mike.diploma.model.Menu;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface Menujpa {
    List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restId) throws SQLException;

    Menu getMenu(int menuId);

    void deleteMenu(int menuId) throws SQLException;

    void addMenu(Menu menu) throws Exception;

    List<Menu> getAllMenuDate(LocalDate localDate) throws SQLException;

    void updateMenu(Menu menu, int restId) throws SQLException;
}
