package ru.mike.diploma.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.services.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {

    final static Logger LOG = LoggerFactory.getLogger(AdminRestaurantController.class);
    @Autowired
    RestaurantService restaurantService;
    static final String URL = "/rest/admin/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> add(@Valid @RequestBody Restaurant restaurant) {
        Restaurant restaurantNew = restaurantService.addOrUpdate(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(restaurantNew.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurantNew);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") int id, @Valid @RequestBody Restaurant restaurant) {
        restaurantService.addOrUpdate(restaurant);
    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }

    @GetMapping(value = "/getAllTodayMenu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getToday() {
        LOG.info("getAllTodayMenu");
        List<Restaurant> restWithTodayMenu = restaurantService.getAllWithTodayMenu();
        LOG.info("size={}", restWithTodayMenu.size());
        return restWithTodayMenu;
    }

}
