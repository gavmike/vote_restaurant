package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> getMenuByLocalDateAndRestaurantId(LocalDate localDate, int restId);

    List<Menu> getAllMenuByRestaurantId(int restId);

    Menu getMenuByIdAndRestaurantId(int menuId, int restId);

    int deleteByIdAndRestaurantId(int menuId, int restId);

}
