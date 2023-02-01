package com.example.onlineshop2.Models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class DTOhistory {
    private Long id;
    private String name;
    private BigDecimal price;
    private Date date;
    private double rating;

    public DTOhistory() {
    }

    public DTOhistory(Long id, String name, BigDecimal price, Date date, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOhistory that = (DTOhistory) o;
        return Double.compare(that.rating, rating) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, date, rating);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "DTOhistory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}
