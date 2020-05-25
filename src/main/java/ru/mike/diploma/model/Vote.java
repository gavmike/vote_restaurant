package ru.mike.diploma.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vote")

public class Vote extends AbstractBaseEntity {
    @Column(name = "date")
    private LocalDate localDate;

    @JoinColumn(name = "id_rest")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user")
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDate localDate, Restaurant restaurant, User user) {
        super(id);
        this.localDate = localDate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(LocalDate localDate, Restaurant restaurant, User user) {
        this(null,localDate,restaurant,user);

    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "localDate=" + localDate +
                ", restaurant=" + restaurant +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}