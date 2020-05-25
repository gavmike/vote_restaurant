package ru.mike.diploma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "menu")

public class Menu extends AbstractNamedEntity {
    @Column(name = "price")
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rest")
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDate;

    public Menu(long price, Restaurant restaurant, LocalDate localDate) {
        this(null,null,price,restaurant,localDate);

    }

    public Menu(String name, long price, LocalDate localDate) {
        this(null,name,price,null,localDate);
          }

    public Menu(String name, long price, Restaurant restaurant, LocalDate localDate) {
        this(null,name,price,restaurant,localDate);
         }


    public Menu( Integer id,String name, long price, Restaurant restaurant, LocalDate localDate) {
        super(name, id);
        this.price = price;
        this.restaurant = restaurant;
        this.localDate = localDate;
    }

    public Menu( Integer id,String name, long price, LocalDate localDate) {
        this(id,name,price,null,localDate);
       }

    public Menu() {
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}