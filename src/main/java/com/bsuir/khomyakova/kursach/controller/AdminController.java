package com.bsuir.khomyakova.kursach.controller;

import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

//@Controller
//@RolesAllowed("USER")
//@RequestMapping("/user")
public class AdminController {
//    @Autowired
//    private AppUserRepository appUserRepository;
//
//    @GetMapping
//    public String userList(Model model){
//        model.addAttribute("users",appUserRepository.findAll());
//        return "userList";
//    }
}
