package com.example.onlineshop2.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserStrory {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @OneToMany
    private List<Zakaz> zakaz;
}
