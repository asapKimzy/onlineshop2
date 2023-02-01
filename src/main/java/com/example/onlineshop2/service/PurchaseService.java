package com.example.onlineshop2.service;

import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.Models.Rating;
import com.example.onlineshop2.repositories.ManufacturerRepository;
import com.example.onlineshop2.repositories.ProductTypeRepository;
import com.example.onlineshop2.repositories.PurhaseRepository;
import com.example.onlineshop2.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    RatingRepository ratingRepository;
    public void countAverageMark(Purchase purchase) {
        List<Rating> list = ratingRepository.findByPurchase(purchase);
        if (list.size() != 0) {
            Double average = list.stream().mapToDouble(x -> x.getGrade()).average().getAsDouble();
            purchase.setAverageMark(average);
            purhaseRepository.save(purchase);
        }
    }
}
