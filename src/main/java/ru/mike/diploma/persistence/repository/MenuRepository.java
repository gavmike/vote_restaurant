package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> getMenuByLocalDateAndRestaurantId(LocalDate localDate, int restId);

    List<Menu> getAllMenuByRestaurantId(int restId);

    Menu getMenuByIdAndRestaurantId(int menuId, int restId);

    @Transactional
    int deleteByIdAndRestaurantId(int menuId, int restId);

}
