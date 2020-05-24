package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findAll();
   @Query("select r from Restaurant r join fetch r.menuList join r.votes")
    public List<Restaurant> getAllWithTodayMenu();

}
