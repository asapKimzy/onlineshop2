package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Manufacturer;
import com.example.onlineshop2.Models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    boolean existsByName(String name);
    List<ProductType> findAll();

    ProductType findByName(String name);
}
