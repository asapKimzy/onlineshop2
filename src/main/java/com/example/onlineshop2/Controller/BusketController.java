package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.Busket;
import com.example.onlineshop2.Models.Purchase;
import com.example.onlineshop2.Models.User;
import com.example.onlineshop2.repositories.BusketRepository;
import com.example.onlineshop2.repositories.PurhaseRepository;
import com.example.onlineshop2.service.BusketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BusketController {
    @Autowired
    BusketRepository busketRepository;
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    BusketService busketService;

    @GetMapping ("personalBusket")
    public String busket( @AuthenticationPrincipal User user, Model model){
           if(busketRepository.existsByUser(user)){

               Busket busket = busketRepository.findByUser(user);
               Map<Purchase,Integer> map=busket.getPurchases();
               //int number=map.get(purhaseRepository.findById(Long.valueOf(id)).get());
              // BigDecimal cost=busket.getCost().multiply(BigDecimal.valueOf(number));
               BigDecimal cost=busketService.busketCost(user);
               model.addAttribute("busket", busket.getPurchases());
               model.addAttribute("id", busket.getId());
             //  model.addAttribute("number", );
               model.addAttribute("cost", cost);
               return "/personalBusket";
           }
           else{
               return "failPage";
           }

    }
    @GetMapping("clickMinus")
    public String clickButton(@RequestParam (name = "id")String id, @AuthenticationPrincipal User user, Model model) {
        Busket busket = busketRepository.findByUser(user);
        Map<Purchase, Integer> map = busket.getPurchases();
        Purchase purchase = purhaseRepository.findById(Long.valueOf(id)).get();
        int number = map.get(purhaseRepository.findById(Long.valueOf(id)).get()) - 1;
        if (number >= 0) {
            map.replace(purhaseRepository.findById(Long.valueOf(id)).get(), number);
            busket.setPurchases(map);
            BigDecimal cost = busketService.busketCost(user);
            busket.setCost(cost);
            busketRepository.save(busket);
            model.addAttribute("number", purchase.getNumber());
            return "redirect:/personalBusket";
        } else {
            map.replace(purhaseRepository.findById(Long.valueOf(id)).get(), 0);
            busket.setPurchases(map);
            BigDecimal cost = busketService.busketCost(user);
            busket.setCost(cost);
            busketRepository.save(busket);
            model.addAttribute("number", purchase.getNumber());
            return "redirect:/personalBusket";
        }
    }
        @GetMapping("clickDelete")
        public String clickDeleteButton(@RequestParam (name = "id")String id, @AuthenticationPrincipal User user, Model model){
            Busket busket = busketRepository.findByUser(user);
            Map<Purchase, Integer> map = busket.getPurchases();
            Purchase purchase = purhaseRepository.findById(Long.valueOf(id)).get();
            map.remove(purchase);
            busket.setPurchases(map);
            busketRepository.save(busket);
            return "redirect:/personalBusket";
        }

     //  @GetMapping("maptest")
     //      public String maptest(Model model){
     //         return "map";
     //              }

    @GetMapping("clickPlus")
    public String clickButton2(@RequestParam (name = "id")String id, @AuthenticationPrincipal User user, Model model){
        Busket busket=busketRepository.findByUser(user);
        Map<Purchase,Integer> map=busket.getPurchases();
        Purchase purchase=purhaseRepository.findById(Long.valueOf(id)).get();
        int number=map.get(purhaseRepository.findById(Long.valueOf(id)).get())+1;
        if(number>purchase.getNumber()){
            map.replace(purhaseRepository.findById(Long.valueOf(id)).get(), purchase.getNumber());
            busket.setPurchases(map);
            BigDecimal cost=busketService.busketCost(user);
            busket.setCost(cost);
            busketRepository.save(busket);
            model.addAttribute("number",purchase.getNumber() );
            return "redirect:/personalBusket";
        }
        else {
            map.replace(purhaseRepository.findById(Long.valueOf(id)).get(), number);
            busket.setPurchases(map);
            BigDecimal cost=busketService.busketCost(user);
            busket.setCost(cost);
            busketRepository.save(busket);
            model.addAttribute("number",purchase.getNumber() );
            return "redirect:/personalBusket";
        }

    }


    @PostMapping ("busket")
    public String addToBusket(@RequestParam (name = "id")String id, @AuthenticationPrincipal User user){
        if(busketRepository.existsByUser(user)){

            if(!checkPuchase(id, user)){
                Busket busket=busketRepository.findByUser(user);
                Map<Purchase,Integer> map=busket.getPurchases();
                map.put(purhaseRepository.findById(Long.valueOf(id)).get(),1 );
                busket.setPurchases(map);
                BigDecimal cost=busketService.busketCost(user);
                busket.setCost(cost);
                busket.setCost(purhaseRepository.findById(Long.valueOf(id)).get().getPrice());
                busket.setUser(user);
                busketRepository.save(busket);
            }
            else {
                Busket busket=busketRepository.findByUser(user);
                Map<Purchase,Integer> map=busket.getPurchases();
                int number=map.get(purhaseRepository.findById(Long.valueOf(id)).get())+1;
                map.replace(purhaseRepository.findById(Long.valueOf(id)).get(), number);
                busket.setPurchases(map);
                BigDecimal cost=busketService.busketCost(user);
                busket.setCost(cost);
                busket.setCost(purhaseRepository.findById(Long.valueOf(id)).get().getPrice());
                busket.setUser(user);
                busketRepository.save(busket);
            }
        }
        else {
            Busket busket1=new Busket();
            Map<Purchase, Integer> map=new HashMap<>();
            map.put(purhaseRepository.findById(Long.valueOf(id)).get(),1 );
            busket1.setPurchases(map);
            busket1.setCost(purhaseRepository.findById(Long.valueOf(id)).get().getPrice());
            busket1.setUser(user);
            busketRepository.save(busket1);
        }
        return "redirect:/mainShop";
    }
    public boolean checkPuchase(String id, User user) {
        Busket busket = busketRepository.findByUser(user);
        Map<Purchase, Integer> map = busket.getPurchases();

        Purchase purchase = purhaseRepository.findById(Long.valueOf(id)).get();
        boolean check=map.keySet().stream().map(x->x.getId()).anyMatch(x->x==purchase.getId());
        if (check) {
            return true;
        } else {
            return false;
        }
    }
}
