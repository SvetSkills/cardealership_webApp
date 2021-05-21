package com.bsuir.khomyakova.kursach.service;

import com.bsuir.khomyakova.kursach.model.AppUser;
import com.bsuir.khomyakova.kursach.model.AppUserRole;

import java.util.List;

public interface AppUserService {
    List<AppUser> findAll();

    AppUser findByPassword(String password);

    AppUser findByEmail(String email);

    AppUser findByRole(AppUserRole appUserRole);

    AppUser findById(Long id);

    void delete(Long id);

    void update(AppUser user);

    void add(AppUser user);
}
