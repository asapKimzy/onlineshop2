package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.Manufacturer;
import com.example.onlineshop2.Models.ProductType;
import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.Models.Rating;
import com.example.onlineshop2.repositories.ManufacturerRepository;
import com.example.onlineshop2.repositories.ProductTypeRepository;
import com.example.onlineshop2.repositories.PurhaseRepository;
import com.example.onlineshop2.repositories.RatingRepository;
import com.example.onlineshop2.service.FilterService;
import com.example.onlineshop2.service.PurchaseService;
import jdk.jfr.DataAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainShop {
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    FilterService filterService;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/mainShop")
    public String opensite(@RequestParam(name = "sel_name", required = false)String tag, Model model){
        List<Purchase> purLIst=purhaseRepository.findAll();
        for (int i = 0; i < purLIst.size(); i++) {
            purchaseService.countAverageMark(purLIst.get(i));

        }
         purLIst=purhaseRepository.findAll();
        if(tag==null||tag.equals("0")){
            model.addAttribute("purchases", purLIst);

        }
        else if(tag.equals("date_new")){
            List<Purchase> list=sortByDateNew(purLIst);
            model.addAttribute("purchases", list);
        } else if (tag.equals("date_old")) {
            List<Purchase> list=sortByDateOld(purLIst);
            model.addAttribute("purchases", list);
        } else if (tag.equals("from_most_expensive")) {
            List<Purchase> list=sortFromExpensive(purLIst);
            model.addAttribute("purchases", list);
        }
         else if (tag.equals("from_cheapest")) {
            List<Purchase> list = sortFromCheapest(purLIst);
            model.addAttribute("purchases", list);
        }
         List<Manufacturer> list=manufacturerRepository.findAll();
         List<String> names=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            names.add(list.get(i).getName());
        }
        List<String> typeNames=new ArrayList<>();
        List<ProductType> list2=productTypeRepository.findAll();
        for (int i = 0; i < list2.size(); i++) {
            typeNames.add(list2.get(i).getName());
        }
         model.addAttribute("options", names);
        model.addAttribute("types", typeNames);
        return "mainShop";
    }
    @PostMapping("/filter") public String filter(@RequestParam(name = "min",required = false) String min,
                                                  @RequestParam(name = "max",required = false) String max,
                                                  @RequestParam(name = "productType", required = false)String productType,
                                                  @RequestParam(name = "productManuf", required = false)String productManuf, Model model){
        List<Purchase> list=filterService.filter(min, max,productType,productManuf);

        List<Manufacturer> list1=manufacturerRepository.findAll();
        List<String> names=new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            names.add(list1.get(i).getName());
        }
        List<String> typeNames=new ArrayList<>();
        List<ProductType> list2=productTypeRepository.findAll();
        for (int i = 0; i < list2.size(); i++) {
            typeNames.add(list2.get(i).getName());
        }
        model.addAttribute("options", names);
        model.addAttribute("types", typeNames);
        model.addAttribute("purchases", list);
        return "mainShop";
    }

    public List<Purchase> sortByDateNew(List<Purchase> purList){
           List<Purchase> sortNewDate=purList.stream().sorted((y,x)->x.getDate().compareTo(y.getDate())).toList();
           return sortNewDate;
    }
    public List<Purchase> sortByDateOld(List<Purchase> purList){
        List<Purchase> sortOldDate=purList.stream().sorted((y,x)->y.getDate().compareTo(x.getDate())).toList();
        return sortOldDate;
    }
    public List<Purchase> sortFromCheapest(List<Purchase> purList){
      List<Purchase> sortCheapPrice = purList.stream().sorted((y,x)->y.getPrice().compareTo(x.getPrice())).toList();
      return sortCheapPrice;
    }
    public List<Purchase> sortFromExpensive(List<Purchase> purList){
        List<Purchase> sortExpensivePrice = purList.stream().sorted((y,x)->x.getPrice().compareTo(y.getPrice())).toList();
        return sortExpensivePrice;
    }

}
