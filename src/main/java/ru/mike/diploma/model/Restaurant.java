package ru.mike.diploma.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurant")

public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonManagedReference
    private List<Menu> menuList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Vote> votes;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Restaurant(List<Menu> menuList, List<Vote> votes) {
        this.menuList = menuList;
        this.votes = votes;
    }

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name, null);
    }

    public Restaurant(int id, String name) {
        super(name, id);
    }

    public Restaurant(String name, Integer id) {
        super(name, id);
    }

    public Restaurant(String name, Integer id, List<Menu> menuList, List<Vote> votes) {
        super(name, id);
        this.menuList = menuList;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                " name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
