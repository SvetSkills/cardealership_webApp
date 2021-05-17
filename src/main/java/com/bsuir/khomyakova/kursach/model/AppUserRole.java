package com.bsuir.khomyakova.kursach.model;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
