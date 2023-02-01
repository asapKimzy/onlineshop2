package com.example.onlineshop2.service;

import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.repositories.ManufacturerRepository;
import com.example.onlineshop2.repositories.ProductTypeRepository;
import com.example.onlineshop2.repositories.PurhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
   public List<Purchase> filter(String min, String max, String productType, String productManuf){
       List<Purchase> purchaseList=new ArrayList<>();
       if(min.equals("")&&max.equals("")&&productType.equals("0")&&productManuf.equals("0")){
        purchaseList=purhaseRepository.findAll();

       }
       else if (min.equals("")&&productType.equals("0")&&productManuf.equals("0")) {
           purchaseList=purhaseRepository.findByPriceBefore(BigDecimal.valueOf(Long.valueOf(max)));
       }
       else if (max.equals("")&&productType.equals("0")&&productManuf.equals("0")) {
           purchaseList=purhaseRepository.findByPriceAfter(BigDecimal.valueOf(Long.valueOf(min)));


       } else if (max.equals("")&&min.equals("")&&productType.equals("0")) {
           purchaseList=purhaseRepository.findAll().stream().filter(x->x.getManufacturerName().equals(productManuf)).toList();

       } else if (max.equals("")&&min.equals("")&&productManuf.equals("0")){
           purchaseList=purhaseRepository.findAll().stream().filter(x->x.getProductType().getName().equals(productType)).toList();

       } else if (max.equals("")&&productManuf.equals("0")) {
        List<Purchase> list=purhaseRepository.findByPriceAfter(BigDecimal.valueOf(Long.valueOf(min)));
        List<Purchase>list1=list.stream().filter(x->x.getProductType().getName().equals(productType)).toList();
        purchaseList=list1;
    }
       else if (max.equals("")&&productType.equals("0")) {
           List<Purchase> list=purhaseRepository.findByPriceAfter(BigDecimal.valueOf(Long.valueOf(min)));
           List<Purchase>list1=list.stream().filter(x->x.getManufacturerName().equals(productManuf)).toList();
           purchaseList=list1;
       }
       else if (min.equals("")&&productManuf.equals("0")) {
           List<Purchase> list=purhaseRepository.findByPriceBefore(BigDecimal.valueOf(Long.valueOf(max)));
           List<Purchase>list2=list.stream().filter(x->x.getProductType().getName().equals(productType)).toList();
           purchaseList=list2;
       }
       else if(min.equals("")&&productType.equals("0")){
           List<Purchase> list=purhaseRepository.findByPriceBefore(BigDecimal.valueOf(Long.valueOf(max)));
           List<Purchase>list1=list.stream().filter(x->x.getManufacturerName().equals(productManuf)).toList();
           purchaseList=list1;
       }
       else if (productType.equals("0")&&productManuf.equals("0")) {
           purchaseList=purhaseRepository.findByPriceBetween(BigDecimal.valueOf(Long.valueOf(min)), BigDecimal.valueOf(Long.valueOf(max)));

       } else if (max.equals("")&&min.equals("")) {
           List<Purchase> list=purhaseRepository.findAll().stream().filter(x->x.getManufacturerName().equals(productManuf)).toList();
           purchaseList=list.stream().filter(x->x.getProductType().getName().equals(productType)).toList();

       } else if (productManuf.equals("0")) {
           List<Purchase>list=purhaseRepository.findByPriceBetween(BigDecimal.valueOf(Long.valueOf(min)), BigDecimal.valueOf(Long.valueOf(max)));
           List<Purchase>list1=list.stream().filter(x->x.getProductType().getName().equals(productType)).toList();
           purchaseList=list1;

       } else if (productType.equals("0")) {
           List<Purchase> list = purhaseRepository.findByPriceBetween(BigDecimal.valueOf(Long.valueOf(min)), BigDecimal.valueOf(Long.valueOf(max)));
           List<Purchase> list1 = list.stream().filter(x -> x.getManufacturerName().equals(productManuf)).toList();
           purchaseList = list1;
       }else if (max.equals("")) {
           purchaseList=purhaseRepository.findByPriceAfter(BigDecimal.valueOf(Long.valueOf(min)));
       } else if (min.equals("")) {
           purchaseList=purhaseRepository.findByPriceBefore(BigDecimal.valueOf(Long.valueOf(max)));
       }
       else {
           List<Purchase>list=purhaseRepository.findByPriceBetween(BigDecimal.valueOf(Long.valueOf(min)), BigDecimal.valueOf(Long.valueOf(max)));
           List<Purchase>list1=list.stream().filter(x->x.getManufacturerName().equals(productManuf)).toList();
           purchaseList=list1.stream().filter(x->x.getProductType().getName().equals(productType)).toList();

       }
return purchaseList;
   }
}
