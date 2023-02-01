package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Manufacturer;
import com.example.onlineshop2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {


    boolean existsManufacturerByName(String manufName);
    Manufacturer findByName(String name);
    List<Manufacturer> findAll();
}
