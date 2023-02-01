package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.Models.Rating;
import com.example.onlineshop2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByPurchase(Purchase purchase);
    //Rating findById(String id);
    boolean existsByPurchaseAndUser(Purchase purchase, User user);
    Rating findByPurchaseAndUser(Purchase purchase, User user);

}
