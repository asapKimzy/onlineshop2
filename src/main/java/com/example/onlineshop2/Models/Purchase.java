package com.example.onlineshop2.Models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private BigDecimal price;
    @OneToOne
    private ProductType productType;
    private int number;
    private String picName;
    @Column(length = 5000)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id && number == purchase.number && Objects.equals(name, purchase.name) && Objects.equals(price, purchase.price) && Objects.equals(productType, purchase.productType) && Objects.equals(picName, purchase.picName) && Objects.equals(description, purchase.description) && Objects.equals(manufacturer, purchase.manufacturer) && Objects.equals(date, purchase.date) && Objects.equals(grades, purchase.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, productType, number, picName, description, manufacturer, date, grades);
    }

    @OneToOne
    private Manufacturer manufacturer;
    private Date date;
    @OneToMany
    private List<Rating> grades;
    private Double averageMark;

    public Purchase(long id, String name, BigDecimal price, ProductType productType, int number, String description, Manufacturer manufacturer, Date date,String picName, List<Rating> grades) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.number = number;
        this.picName=picName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.date = date;
        this.grades = grades;
    }

    public Purchase() {
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public long getId() {
        return id;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

    public void setId(long id) {
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    public String getManufacturerName(){
        return manufacturer.getName();
    }
    public String getProductTypeName(){
        return productType.getName();
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Rating> getGrades() {
        return grades;
    }

    public void setGrades(List<Rating> grades) {
        this.grades = grades;
    }
    public double getSum(List<Rating> grades){
        double sum=0;
        for (int i = 0; i < grades.size(); i++) {
            sum=sum+grades.get(i).getGrade();
        }
        return sum;
    }
}
