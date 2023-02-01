package com.example.onlineshop2.repositories;

import com.example.onlineshop2.Models.User;
import com.example.onlineshop2.Models.Zakaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZakazRepository extends JpaRepository<Zakaz,Long> {
  List<Zakaz> findByUser(User user);
}
