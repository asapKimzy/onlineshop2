package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.*;
import com.example.onlineshop2.repositories.*;
import com.example.onlineshop2.service.BusketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class OrderController {
    @Autowired
    BusketRepository busketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BusketService busketService;
    @Autowired
    ZakazRepository zakazRepository;
    @Autowired
    ProductInBusketRepository productInBusketRepository;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("clickBuy")
    public String clickBuyButton(@AuthenticationPrincipal User user,  Model model){
        List<Address> list =addressRepository.findByUser(user);
        List<String> addres=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
          String add=  list.get(i).getHomeAdd();
          addres.add(add);
        }
        BigDecimal cost=busketService.busketCost(user);
        model.addAttribute("options",addres);
        model.addAttribute("cost", cost);
        return "order";
    }
@PostMapping("clickOrder")
    public String clickOrderButton(@AuthenticationPrincipal User user,
                                   @RequestParam(name = "selection", required = false)String country, @RequestParam(name = "houseadd", required = false)String addres,
                                   @RequestParam(name = "city", required = false)String city, @RequestParam(name = "tel", required = false)String number,
                                   @RequestParam( name = "email", required = false)String email, @RequestParam(name = "select",required = false)String address1, Model model){
             Zakaz zakaz=new Zakaz();
             zakaz.setUser(user);
             Address address=new Address();
             address.setCity(city);
             address.setUser(user);
             address.setCountry(country);
             address.setHomeAdd(addres);
             addressRepository.save(address);
             zakaz.setAddress(address);
             zakaz.setEmail(email);
             zakaz.setDate(new Date());
             Busket busket=busketRepository.findByUser(user);
             Map<Purchase, Integer> map = busket.getPurchases();
             Set<Purchase> set=map.keySet();
             List<ProductInBusket> list=new ArrayList<>();
             for (Purchase i: set) {
              ProductInBusket productInBusket=new ProductInBusket();
              productInBusket.setPurchase(i);
              list.add(productInBusket);
              productInBusketRepository.save(productInBusket);
          }

          zakaz.setList(list);
                   zakazRepository.save(zakaz);
                   busket.getPurchases().clear();
                   busketRepository.save(busket);
                   return "succesOrder";
    }
}
