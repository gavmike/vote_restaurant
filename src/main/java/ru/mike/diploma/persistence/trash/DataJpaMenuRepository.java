package ru.mike.diploma.persistence.trash;

import ru.mike.diploma.model.Menu;

import java.time.LocalDate;
import java.util.List;

public class DataJpaMenuRepository implements MenuRepository2 {
    public final CrudMenuRepository crudMenuRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository) {
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    public Menu add(Menu menu) {
        return crudMenuRepository.save(menu);
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.getOne(id);
    }

    @Override
    public void delete(int id) {
        crudMenuRepository.deleteById(id);

    }

    @Override
    public List<Menu> getAllbyDateAndRestaurantId(LocalDate localDate, int restId) {
        return crudMenuRepository.getAllbyDateAndRestaurantId(localDate,restId);
    }

    @Override
    public List<Menu> getAllbyRestaurantId(int restId) {
        return crudMenuRepository.getAllbyRestaurantId(restId);
    }
}
