package ru.mike.diploma.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.persistence.repository.MenuRepository;
import ru.mike.diploma.persistence.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;


import static ru.mike.diploma.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Menu add(Menu menu, int rest_id) {
        Assert.notNull(menu, "the menu should not be empty");
        Restaurant rest = checkNotFoundWithId(restaurantRepository.findById(rest_id).get(), rest_id);
        menu.setRestaurant(rest);
        menuRepository.save(menu);
        return menu;
    }

    public List<Menu> getAllByDateAndRestaurantId(LocalDate localDate, int restId) {
        return menuRepository.getMenuByLocalDateAndRestaurantId(localDate, restId);
    }

    public Menu get(int menuId, int restId) {
        return menuRepository.getMenuByIdAndRestaurantId(menuId, restId);
    }

    public void delete(int menuId, int restId) {
        checkNotFoundWithId(menuRepository.deleteByIdAndRestaurantId(menuId, restId) != 0, menuId);
    }

    public List<Menu> getAllByRestaurantId(int restId) {
        return menuRepository.getAllMenuByRestaurantId(restId);
    }
}
