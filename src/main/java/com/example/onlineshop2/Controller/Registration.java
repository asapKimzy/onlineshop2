package com.example.onlineshop2.Controller;

import com.example.onlineshop2.Models.Role;
import com.example.onlineshop2.Models.User;
import com.example.onlineshop2.repositories.UserRepository;
import com.example.onlineshop2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Registration {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping ("/registration")
    public String registr(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "registration";
    }
@PostMapping("/registration")
    public String registration(@RequestParam(name="username")String username,
                               @RequestParam(name = "password")String password,
                               @RequestParam(name = "passwordRep")String passwordRepeat,
                               @RequestParam(name = "email")String email, Model model){
        User user=new User();
        if(!userRepository.existsUserByUsername(username)&&password.equals(passwordRepeat)){
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            List<Role> list=new ArrayList<>();
            list.add(Role.USER);
            user.setRoles(list);
            user.setActive(true);
            userRepository.save(user);
            model.addAttribute("user", user);
            return "login";
        }
        else {
            model.addAttribute("error", userService.checkError(username, password, passwordRepeat));
            return "registration";
        }
}

}
