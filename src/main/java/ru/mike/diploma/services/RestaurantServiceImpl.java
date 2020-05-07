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
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    final static Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    VoteService voteService;

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Override
    public void delete(int id) {
        get(id);
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        Assert.notNull(restaurant, "the Restaurant should not be empty");
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "the Restaurant should not be empty");
        restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getWithTodayMenu(int restId) {
        List<Menu> menu = menuRepository.getMenuByLocalDateAndRestaurantId(LocalDate.now(), restId);
        LOG.info("menu={}", menu);
        Restaurant restaurant = (Restaurant) get(restId);
        restaurant.setMenuList(menu);
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllWithTodayMenu(LocalDate localDate) {
        List<Restaurant> allRest = getAll();
        for (Restaurant rest : allRest) {
            LOG.info("rest={}", rest);
            List<Menu> menu = menuRepository.getMenuByLocalDateAndRestaurantId(LocalDate.now(), rest.getId());
            List<Vote> votes = voteService.getAllByRestaurantIdAndDate(rest.getId(), LocalDate.now());
            LOG.info("menu={}", menu);
            rest.setMenuList(menu);
            rest.setVotes(votes);

        }
        List<Restaurant> restTodayMenu = allRest.stream().filter(x -> !x.getMenuList().isEmpty()).collect(Collectors.toList());
        LOG.debug("size={}", restTodayMenu.size());
        return restTodayMenu;
    }
}
