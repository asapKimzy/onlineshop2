package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.*;
import com.example.onlineshop2.repositories.ManufacturerRepository;
import com.example.onlineshop2.repositories.ProductTypeRepository;
import com.example.onlineshop2.repositories.PurhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class ManufacturerController {
    @Autowired
    ManufacturerRepository manufacturerRepository;
   @Autowired
    ProductTypeRepository productTypeRepository;

@GetMapping("/manufacturer")
   public String addManufacturer( Model model){

    model.addAttribute("options", Country.values());
    return "manufacturer";
}
@PostMapping ("/manufacturer")
    public String addingMan(@RequestParam(name = "manufName")String name, @RequestParam(name = "country")Country country, Model model){
    if(!manufacturerRepository.existsManufacturerByName(name)){
        Manufacturer manufacturer=new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setCountry(String.valueOf(country));
        manufacturerRepository.save(manufacturer);
        model.addAttribute("manufName", manufacturer.getName());
        return "manufacturer";
    }
    else {
        model.addAttribute("error", "Manufacturer already exists");
        return "manufacturer";
    }
}
@PostMapping("productType")
public String addingPrType(@RequestParam(name = "prodName")String name, Model model){
    if(!productTypeRepository.existsByName(name)){
       ProductType productType=new ProductType();
        productType.setName(name);
        productTypeRepository.save(productType);
        model.addAttribute("prodName", productType.getName());
        return "manufacturer";
    }
    else {
        model.addAttribute("error", "Category already exists");
        return "manufacturer";
    }
}

}
