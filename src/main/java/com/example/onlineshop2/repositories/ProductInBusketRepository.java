package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.ProductInBusket;
import com.example.onlineshop2.Models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInBusketRepository extends JpaRepository<ProductInBusket, Long> {

}
