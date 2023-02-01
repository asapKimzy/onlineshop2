package com.example.onlineshop2.service;

import com.example.onlineshop2.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

}
