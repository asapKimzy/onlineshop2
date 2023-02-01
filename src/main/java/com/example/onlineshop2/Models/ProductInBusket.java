package com.example.onlineshop2.Models;

import javax.persistence.*;

@Entity
public class ProductInBusket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Purchase purchase;


    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }


}
