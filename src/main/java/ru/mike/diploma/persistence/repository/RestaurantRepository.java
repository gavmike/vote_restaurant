package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.Restaurant;

import java.util.List;

@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findAll();

}
