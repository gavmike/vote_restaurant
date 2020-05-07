package ru.mike.diploma.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.services.RestaurantService;
import ru.mike.diploma.services.RestaurantServiceImpl;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.URL)
public class AdminRestaurantController {

    final static Logger LOG = LoggerFactory.getLogger(AdminRestaurantController.class);
    @Autowired
    RestaurantService restaurantService;
    static final String URL = "/admin/restaurant";

    @GetMapping(value = "/get/{id}")
    public Restaurant getRestId(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant) {
        Restaurant restaurantNew = restaurantService.add(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(restaurantNew.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurantNew);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {

        restaurantService.update(restaurant);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }

    @GetMapping(value = "/getAllTodayMenu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getToday() {
        LOG.info("getAllTodayMenu");
        List<Restaurant> restWithTodayMenu = restaurantService.getAllWithTodayMenu(LocalDate.now());
        LOG.info("size={}", restWithTodayMenu.size());
        return restWithTodayMenu;

    }


}
