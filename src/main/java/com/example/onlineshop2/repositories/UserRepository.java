package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.Address;
import com.example.onlineshop2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public  boolean existsUserByUsername(String username);

    UserDetails findByUsername(String username);

}
