package ru.mike.diploma.persistence.trash;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> getAllbyDateAndRestaurantId(LocalDate localDate, int restId);

    List<Menu> getAllbyRestaurantId(int restId);
}
