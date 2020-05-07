package ru.mike.diploma.persistence.trash;

import ru.mike.diploma.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository2 {

    Optional<Restaurant> get(int id);

    void delete(int id);

    Restaurant add(Restaurant restaurant);

    void update(Restaurant restaurant);

    List<Restaurant> getAllRestaurant();

    Restaurant getRestaurantWithTodayMenu(int restId);

    List<Restaurant> getAllWithTodayMenu(LocalDate localDate);
}
