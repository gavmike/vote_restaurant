package ru.mike.diploma.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findAll();










}
