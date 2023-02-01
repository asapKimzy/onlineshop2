package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.*;
import com.example.onlineshop2.repositories.PurhaseRepository;
import com.example.onlineshop2.repositories.RatingRepository;
import com.example.onlineshop2.repositories.ZakazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class StoryController {
    @Autowired
    ZakazRepository zakazRepository;
    @Autowired
    PurhaseRepository purhaseRepository;
    @Autowired
    RatingRepository ratingRepository;
    @GetMapping("/userstory")
    public String opensite(@AuthenticationPrincipal User user, Model model){
        List<Zakaz> story=zakazRepository.findByUser(user);
        List<DTOhistory> purchases=getProduct(story);
        List<DTOhistory> list=prepareMap(purchases);
        List<Integer> integerList=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            integerList.add(i+1);
        }
        model.addAttribute("options", integerList);
        model.addAttribute("story", list);
        return "userstory";
    }
    @PostMapping("/rate")
    private String makeRate(@RequestParam(name = "id")String id,
                            @AuthenticationPrincipal User user,
                            @RequestParam(name = "option")String option){
        //if(ratingRepository.findById(Long.valueOf(id))==null){
        if (ratingRepository.existsByPurchaseAndUser(purhaseRepository.findById(Long.valueOf(id)).get(),user)){
            Rating rating= ratingRepository.findByPurchaseAndUser(purhaseRepository.findById(Long.valueOf(id)).get(),user);
            rating.setGrade(Integer.parseInt(option));
            ratingRepository.save(rating);
        }
        else {
            Rating rating=new Rating();
            rating.setGrade(Integer.parseInt(option));
            rating.setUser(user);
            rating.setPurchase(purhaseRepository.findById(Long.valueOf(id)).get());

            ratingRepository.save(rating);
        }

        return "redirect:/userstory";
    }
    public List<DTOhistory> getProduct(List<Zakaz> story){
        List<DTOhistory> purchases=new ArrayList<>();

        for (int i = 0; i < story.size(); i++) {
            DTOhistory dtOhistory=new DTOhistory();
            List<ProductInBusket> list=story.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                Purchase purchase=list.get(j).getPurchase();
                dtOhistory.setId(purchase.getId());
                dtOhistory.setName(purchase.getName());
                dtOhistory.setPrice(purchase.getPrice());
                dtOhistory.setDate(story.get(i).getDate());
                dtOhistory.setRating(getRate(purchase));
                purchases.add(dtOhistory);
            }
        }
        return purchases;


    }
    public double getRate(Purchase purchase){
List<Rating> ratings=ratingRepository.findByPurchase(purchase);
        if(ratings.size()==0){
            return 0;
        }

        else {
double rate=0;
double sum=1;

        for (int i = 0; i <ratings.size() ; i++) {
            sum=sum + ratings.get(i).getGrade()-1;
        }

            rate=sum/ratings.size();
            return rate;
        }

    }
 public List<DTOhistory> prepareMap(List<DTOhistory> dtOhistories){
       Map<Long,List<DTOhistory>> map=new HashMap<>();
       List<DTOhistory> list=new ArrayList<>();
     for (int i = 0; i < dtOhistories.size(); i++) {
         if(map.containsKey(dtOhistories.get(i).getId())){
             List<DTOhistory> list1=map.get(dtOhistories.get(i).getId());
             list1.add(dtOhistories.get(i));
            map.replace(dtOhistories.get(i).getId(),list1);
         }
         else{
             List<DTOhistory> list1=new ArrayList<>();
             list1.add(dtOhistories.get(i));
             map.put(dtOhistories.get(i).getId(),list1);
         }
     }

     Set<Map.Entry<Long,List<DTOhistory>>> set=map.entrySet();
     for (Map.Entry<Long,List<DTOhistory>>i : set) {
        list.add(i.getValue().stream().sorted((x,y)->y.getDate().compareTo(x.getDate())).findFirst().get());
     }
     return list;
 }
}
