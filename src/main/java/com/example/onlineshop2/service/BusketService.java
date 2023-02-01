package com.example.onlineshop2.service;

import com.example.onlineshop2.Models.Busket;
import com.example.onlineshop2.Models.User;
import com.example.onlineshop2.repositories.BusketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BusketService {
    @Autowired
    BusketRepository busketRepository;
    public BigDecimal busketCost(User user){
        Busket busket=busketRepository.findByUser(user);
        List<BigDecimal> list=busket.getPurchases().entrySet().stream().map(x->x.getKey().getPrice().multiply(BigDecimal.valueOf(x.getValue()))).toList();
        BigDecimal cost=new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            cost=cost.add(list.get(i));
        }
        return cost;
    }
}
