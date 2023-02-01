package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Busket;
import com.example.onlineshop2.Models.Manufacturer;
import com.example.onlineshop2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusketRepository extends JpaRepository<Busket,Long> {
    boolean existsByUser(User user);
    Busket findByUser(User user);
}
