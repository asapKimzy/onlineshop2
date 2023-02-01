package com.example.onlineshop2.Models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
public class Busket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@OneToMany
    @ElementCollection
    @CollectionTable(name = "order_item_mapping",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    //@MapKeyColumn(name = "item_name")
    //@Column(name = "number")
    private Map<Purchase, Integer> purchases;
    @OneToOne
    private User user;
    private BigDecimal cost;

    public Map<Purchase, Integer> getPurchases() {
        return purchases;
    }

    public void setPurchases(Map<Purchase, Integer> purchases) {
        this.purchases = purchases;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }




    public Busket() {
    }

}
