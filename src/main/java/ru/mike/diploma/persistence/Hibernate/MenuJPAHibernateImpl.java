package ru.mike.diploma.persistence.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mike.diploma.model.Menu;
import ru.mike.diploma.persistence.MenuJPA;
import ru.mike.diploma.persistence.jdbc.MenuJPAImplJDBC;

import java.time.LocalDate;
import java.util.List;

@Component
public class MenuJPAHibernateImpl implements MenuJPA {
    @Autowired
    private SessionFactory sessionFactory;
    final static Logger LOG = LoggerFactory.getLogger(MenuJPAImplJDBC.class);

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Menu> getAllMenuDateandRestID(LocalDate localDate, int restId) {
        Query<Menu> query = currentSession().createQuery("from Menu where localDate =:localDate and id_rest =:restId", Menu.class);
        query.setParameter("restId", restId);
        query.setParameter("localDate", localDate);
        LOG.info("query={}", query.getResultList());

        return query.getResultList();
    }

    @Override

    public Menu getMenu(int menuId) {
        Query<Menu> query = currentSession().createQuery("from Menu where id =:menuId", Menu.class);
        query.setParameter("menuId", menuId);
        return query.getSingleResult();
    }

    @Override
    public void deleteMenu(int menuId) {
        Menu menu = getMenu(menuId);
        currentSession().delete(menu);
    }

    @Override
    public void addMenu(Menu menu) {

    }

    @Override
    public List<Menu> getAllMenuDate(LocalDate localDate) {
        Query<Menu> query = currentSession().createQuery("from Menu where localDate =:localDate", Menu.class);
        query.setParameter("localDate", localDate);
        return query.getResultList();
    }

    @Override
    public void updateMenu(Menu menu, int restId) {
        menu.getRestaurant().setId(restId);
        currentSession().update(menu);
        LOG.info("update={}", menu);
        LOG.info("restid={}", menu.getRestaurant().getId());
    }
}
