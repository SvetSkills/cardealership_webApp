package com.bsuir.khomyakova.kursach.controller;
import com.bsuir.khomyakova.kursach.model.*;
import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import com.bsuir.khomyakova.kursach.repository.ClientRepository;
import com.bsuir.khomyakova.kursach.repository.OrderRepository;
import com.bsuir.khomyakova.kursach.service.impl.AppUserService;
import com.bsuir.khomyakova.kursach.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class RegistrationController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private OrderRepository orderRepository;

    private AppUser customUserDetails;

    private Client customClientDetails;

    private Car customCarDetails;

    private int amountOfUsers = 0;

    @GetMapping("")
    public String startPage(Model model) {
        model.addAttribute("user", new AppUser());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new AppUser());
        return "index";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        AppUser appUser = new AppUser();
        AppUserRole role = AppUserRole.USER;
        appUser.setRole(role);
        model.addAttribute("user", new AppUser());
        model.addAttribute("client", new Client());

        return "signUp";
    }

    @GetMapping("/success")
    public String process_success() {
        return "success";
    }


    @PostMapping("/registration")
    public String processRegister(@ModelAttribute("user") AppUser users, @ModelAttribute("client") Client client, Model model) {

        AppUser user = appUserRepository.findAppUserByEmail(users.getEmail());
        System.out.println(users.getUserId());
        if (user == null) {
            clientRepository.save(client);
            users.setClient(client);
            users.setRole(AppUserRole.USER);
            appUserRepository.save(users);
        } else {
            model.addAttribute("message", "Пользователь с таким email уже существует");
            return "signUp";
        }

        return "success";
    }

    @PostMapping("/input")
    public String input(@ModelAttribute("user") AppUser user, BindingResult bindingResult, Model model) {

        AppUser users = appUserRepository.findAppUserByEmail(user.getEmail());
        if (users != null) {

            if (user.getPassword().equals(users.getPassword())) {
                if (amountOfUsers == 0) {
                    amountOfUsers = 1;
                } else {
                    amountOfUsers++;
                }
                System.out.println();
                System.out.println("Количество подключенных пользователей - " + amountOfUsers);
                System.out.println();

            }
            if (user.getPassword().equals(users.getPassword())) {
                customUserDetails = new AppUser();
                customUserDetails.setUserId(users.getUserId());
                customUserDetails.setRole(users.getRole());
                customUserDetails.setEmail(users.getEmail());
                customUserDetails.setPassword(users.getPassword());
                customUserDetails.setClient(users.getClient());
                // System.out.println(customUserDetails.getClient().getClientId());
                switch (customUserDetails.getRole()) {
                    case ADMIN:
                        return "admin";
                    case USER: {
                        //было тут
                        return "user";
                    }
                }
            } else {
                model.addAttribute("message", "Неверно введены данные");
            }
        } else {
            model.addAttribute("message", "Неверно введены данные");
        }


        return "index";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }


    @GetMapping("/adminUserListForAdmin")
    public String clientList(Model model) {
        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("clientList", clientList);
        return "adminUserListForAdmin";
    }

    @PostMapping("/save")
    private String saveClient(@ModelAttribute("client") Client client) {
        clientRepository.save(client);
        return "redirect:/adminUserListForAdmin";
    }
    @GetMapping("/adminInfo")
    private String adminInfo(Model model)
    {
        AppUser users = new AppUser();
        users.setUserId(customUserDetails.getUserId());
        users.setEmail(customUserDetails.getEmail());
        users.setPassword(customUserDetails.getPassword());
        users.setRole(customUserDetails.getRole());
        model.addAttribute("user", users);
        return "adminInfo";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("editClient");
        Client client = clientService.get(id);
        mav.addObject("client", client);

        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        clientService.delete(id);
        return "redirect:/adminUserListForAdmin";
    }

    @GetMapping("/logout")
    public String homePage(Model model) {
        model.addAttribute("user", new AppUser());
        return "index";
    }

    @PostMapping("/saveAdmin")
    private String saveAdmin(@ModelAttribute("user") AppUser users) {
        appUserRepository.save(users);
        customUserDetails = new AppUser();
        customUserDetails.setUserId(users.getUserId());
        customUserDetails.setRole(users.getRole());
        customUserDetails.setEmail(users.getEmail());
        customUserDetails.setPassword(users.getPassword());
        customUserDetails.setClient(users.getClient());
        return "redirect:/adminInfo";
    }

    @GetMapping("/clientInfo")
    private String userInfo(Model model) {
        AppUser users = new AppUser();
        users.setUserId(customUserDetails.getUserId());
        users.setEmail(customUserDetails.getEmail());
        users.setPassword(customUserDetails.getPassword());
        users.setRole(customUserDetails.getRole());
        users.setClient(customUserDetails.getClient());
        model.addAttribute("user", users);
        return "clientInfo";
    }

    @GetMapping("/clientCars")
    public String List(Model model) {

        List<Order> list = orderRepository.findOrderById(customUserDetails.getClient().getClientId());

        model.addAttribute("carsList", list);

        return "clientCars";
    }


    @GetMapping("/saveCarClient/{id}")
    private String saveCarClient(@PathVariable(name = "id") Long id) {
        Car car = carsRepository.findById(id).get();
        Client client = clientRepository.findClientById(customUserDetails.getClient().getClientId());
        //new order

        Order order = new Order();
        order.setClient(client);
        order.setCar(car);

        orderRepository.save(order);

        return "user";
        //return "redirect:/clientCars";
    }


    @GetMapping("/matrix")
    private String setMatrix(Model model) {
        //Integer a11 = request.getParameter("a11");
        Long[][] matrix = new Long[4][4];
        model.addAttribute("matrix1", matrix);
        model.addAttribute("matrixOut", new Matrix(getVerteilungenMatrix(4)));
        return "matrix";
    }

    private List<List<Long>> getVerteilungenMatrix(int size) {
        List<List<Long>> result2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result2.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                result2.get(i).add(Long.valueOf(0));
            }
        }
        return result2;
    }

    @PostMapping("/matrixProcess")
    private String getMatrix(@ModelAttribute("matrixOut") Matrix matrix, Model model) {
        List<List<Long>> list = matrix.getMatrix();
        String j = matrix.findStrategy(list);
        model.addAttribute("strategy", j);

        Long[][] matrix1 = new Long[4][4];
        model.addAttribute("matrix1", matrix1);
        model.addAttribute("matrixOut", new Matrix(getVerteilungenMatrix(4)));
        return "matrix";
    }

}


