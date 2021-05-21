package com.bsuir.khomyakova.kursach.controller;
import com.bsuir.khomyakova.kursach.model.AppUser;
import com.bsuir.khomyakova.kursach.model.Client;
import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import com.bsuir.khomyakova.kursach.repository.ClientRepository;
import com.bsuir.khomyakova.kursach.service.impl.AppUserService;
//import com.bsuir.khomyakova.kursach.service.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegistrationController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppUserService appUserService;



    @GetMapping("")
    public String startPage(Model model){
        model.addAttribute("user", new AppUser());
        return "index";
    }

 @GetMapping("/login")
    public String loginPage(Model model)
    {
        model.addAttribute("user", new AppUser());
        return "index";
    }
/*
    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "index";
    }
*/
    @GetMapping("/signUp")
    public String signUp(Model model)
    {
        model.addAttribute("user", new AppUser());
        model.addAttribute("client", new Client());

        return "signUp";
    }

    @GetMapping("/success")
    public String process_success()
    {
        return "success";
    }


   @PostMapping("/registration")
    public String processRegister(@ModelAttribute("user") AppUser users, @ModelAttribute("client") Client client, Model model)
    {
        AppUser user = appUserRepository.findAppUserByEmail(users.getEmail());
        if (user == null)
        {
            clientRepository.save(client);
           // System.out.println(client.getClientId());
            users.setClient(client);
            appUserRepository.save(users);
        } else
        {
            model.addAttribute("message", "Пользователь с таким email уже существует");
            return "signUp";
        }

        return "success";
    }






//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String indexUser(Model model) {
//        model.addAttribute("films", filmService.getLast());
//        return "/index";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "/login";
//    }
//
//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public String registration(Model model) {
//        model.addAttribute("userForm", new AppUser());
//        return "/registration";
//    }
//
//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") AppUser userForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        userService.add(userForm);
//
//        return "redirect:/";
//    }


}
