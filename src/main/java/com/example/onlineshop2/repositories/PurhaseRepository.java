package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.Models.Rating;
import com.example.onlineshop2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PurhaseRepository extends JpaRepository<Purchase,Long> {
    boolean existsByName(String name);
    List<Purchase> findByPriceBetween(BigDecimal min,BigDecimal max);
    List<Purchase> findByPriceAfter(BigDecimal min);
    List<Purchase> findByPriceBefore(BigDecimal max);
}
