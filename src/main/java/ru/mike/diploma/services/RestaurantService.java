package ru.mike.diploma.services;

import ru.mike.diploma.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {

   Restaurant get(int id);

    void delete(int id);

    Restaurant add(Restaurant restaurant);

    void update(Restaurant restaurant);

    List<Restaurant> getAll();

    Restaurant getWithTodayMenu(int restId);

    List<Restaurant> getAllWithTodayMenu(LocalDate localDate);
}
