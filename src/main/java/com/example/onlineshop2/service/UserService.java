package com.example.onlineshop2.service;

import com.example.onlineshop2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
        @Autowired
        UserRepository userRepository;
        public String checkError(String username, String password, String passwordRepeat){
            if (userRepository.existsUserByUsername(username)){
                return "Username already exists";
            }
            else if (!password.equals(passwordRepeat)) {
                return "Repeat password";
            }
            else {
                return "Username already exists and\n" +
                        " password isn't correct\n";
            }
        }
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    }

