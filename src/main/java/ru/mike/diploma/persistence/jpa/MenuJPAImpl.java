package ru.mike.diploma.persistence.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.persistence.MenuJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class MenuJPAImpl implements MenuJPA {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restId) throws SQLException {
        TypedQuery<Menu> query = entityManager.createQuery("select m from Menu m where m.localDate=:localDate and m.restaurant.id=:restId", Menu.class);
        query.setParameter("localDate", localDate);
        query.setParameter("restId", restId);
        return query.getResultList();
    }

    @Override
    public Menu getMenu(int menuId) {
        TypedQuery<Menu> query = entityManager.createQuery("select m from Menu m where m.id=:menuId", Menu.class);
        query.setParameter("menuId", menuId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteMenu(int menuId) throws SQLException {
        entityManager.remove(getMenu(menuId));
    }

    @Override
    @Transactional
    public void addMenu(Menu menu) throws Exception {
        entityManager.persist(menu);
    }

    @Override
    public List<Menu> getAllMenuDate(LocalDate localDate) throws SQLException {
        TypedQuery<Menu> query = entityManager.createQuery("select m from Menu m where m.localDate=:localDate", Menu.class);
        query.setParameter("localDate", localDate);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu, int restId) throws SQLException {
        Menu newmenu = entityManager.merge(menu);
        newmenu.setPrice(menu.getPrice());
        newmenu.setRestaurant(menu.getRestaurant());
        newmenu.setLocalDate(menu.getLocalDate());
        newmenu.setName(menu.getName());
    }
}
