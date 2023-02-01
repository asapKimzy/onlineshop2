package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.*;
import com.example.onlineshop2.repositories.ManufacturerRepository;
import com.example.onlineshop2.repositories.ProductTypeRepository;
import com.example.onlineshop2.repositories.PurhaseRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PurchaseController {
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Value("${upload.path}")
    private String valueFromFile;

    @GetMapping("/purchase")
    public String addPurchase(Model model) {
        List<String> list = productTypeRepository.findAll().stream().map(x -> x.getName()).collect(Collectors.toList());
        model.addAttribute("options", list);
        return "purchase";
    }

    @PostMapping("/purchase")
    public String addingPur(@RequestParam(name = "purName", required = false) String name, @RequestParam(name = "price", required = false) BigDecimal price,
                            @RequestParam(name = "productType", required = false) String productType, @RequestParam(name = "manuf", required = false) String manufacturer,
                            @RequestParam(name = "description", required = false) String description,
                            @RequestParam(name = "number", required = false) int number, @RequestParam(name = "picture", required = false) MultipartFile picture, Model model) throws IOException {
        // System.out.println(picture.getOriginalFilename());
        // File file = new File(valueFromFile);
        String file1 = valueFromFile + "/" + picture.getOriginalFilename() + "";


        picture.transferTo(new File(file1));

          if(!purhaseRepository.existsByName(name)&&manufacturerRepository.existsManufacturerByName(manufacturer)&&(price!=null)){
              Purchase purchase=new Purchase();
              ProductType  productType1=productTypeRepository.findByName(productType);
              Manufacturer manufacturer1=manufacturerRepository.findByName(manufacturer);
              productType1.setName(productType);
              purchase.setName(name);
              purchase.setNumber(number);
              purchase.setDate(new Date());
              purchase.setDescription(description);
              purchase.setManufacturer(manufacturer1);
              purchase.setProductType(productType1);
              purchase.setPrice(price);
              purchase.setPicName(picture.getOriginalFilename());
              purhaseRepository.save(purchase);
              return "/purchase";
          }
          else {
              model.addAttribute("error", "purchase already exists");
           return "purchase";
           }
    }


}
