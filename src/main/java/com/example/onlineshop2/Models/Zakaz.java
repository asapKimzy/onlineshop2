package com.example.onlineshop2.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Zakaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany
    private List<ProductInBusket> list;
    @ManyToOne
    private Address address;

    private String email;
    private Date date;


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<ProductInBusket> getList() {
        return list;
    }

    public void setList(List<ProductInBusket> list) {
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
