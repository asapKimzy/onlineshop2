package com.example.onlineshop2.Models;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private User user;
    private double grade;
    @ManyToOne
    private Purchase purchase;

    public User getUser() {
        return user;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
