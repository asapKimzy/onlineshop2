package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.repositories.PurhaseRepository;
import com.example.onlineshop2.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class DetailsController {
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping ("/purchase/details/{id}")
    public String showMore(@PathVariable String id, Model model){
        Purchase purchase=purhaseRepository.findById(Long.valueOf(id)).get();

        model.addAttribute("name", purchase.getName());
        model.addAttribute("price", purchase.getPrice());
        model.addAttribute("description", purchase.getDescription());
        model.addAttribute("picture", purchase.getPicName());
        model.addAttribute("grades", purchase.getAverageMark());
        return "details";
    }

}
