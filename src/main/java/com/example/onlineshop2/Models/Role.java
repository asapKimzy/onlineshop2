package com.example.onlineshop2.Models;

import org.springframework.security.core.GrantedAuthority;

import java.security.GeneralSecurityException;

public enum Role implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
