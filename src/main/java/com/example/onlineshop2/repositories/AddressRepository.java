package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Address;
import com.example.onlineshop2.Models.Busket;
import com.example.onlineshop2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByUser(User user);
}
