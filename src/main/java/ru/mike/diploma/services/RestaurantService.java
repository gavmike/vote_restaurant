package ru.mike.diploma.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.persistence.repository.MenuRepository;
import ru.mike.diploma.persistence.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class RestaurantService  {
    final static Logger LOG = LoggerFactory.getLogger(RestaurantService.class);
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    VoteService voteService;


    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow();
    }


    public void delete(int id) {
               restaurantRepository.deleteById(id);
    }


    public Restaurant addOrUpdate(Restaurant restaurant) {
        Assert.notNull(restaurant, "the Restaurant should not be empty");
        return restaurantRepository.save(restaurant);
    }

      public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }


    public Restaurant getWithTodayMenu(int restId) {
        List<Menu> menu = menuRepository.getMenuByLocalDateAndRestaurantId(LocalDate.now(), restId);
        LOG.info("menu={}", menu);
        Restaurant restaurant =  get(restId);
        restaurant.setMenuList(menu);
        return restaurant;
    }


    public List<Restaurant> getAllWithTodayMenu()/*(LocalDate localDate) */{
        List<Restaurant> allRest = getAll();
          List<Restaurant> restTodayMenu = allRest.stream().filter(x -> !x.getMenuList().isEmpty()).collect(Collectors.toList());
        LOG.debug("size={}", restTodayMenu.size());
        return restTodayMenu;
    }
}
