package com.bsuir.khomyakova.kursach.controller;

import com.bsuir.khomyakova.kursach.model.*;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import com.bsuir.khomyakova.kursach.repository.ClientRepository;
import com.bsuir.khomyakova.kursach.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;



@Controller
public class ClientController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ClientRepository clientRepository;


    private Client customClientDetail;

    private Comment customComment;

    @GetMapping("/createComment")
    public String signUp(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("comment", new Comment());

        return "comment";
    }

    @PostMapping("/comment")
    public String inputComment(@ModelAttribute("client")Client client, BindingResult bindingResult, Model model) {

        Client client1=clientRepository.findClientById(client.getClientId());
//        AppUser users = appUserRepository.findAppUserByEmail(user.getEmail());
        if(client.getClientId().equals(client1.getClientId())){
           customClientDetail=new Client();
//            customComment = new Comment();
//            customComment.setText(client1.getComment().getText());
            customClientDetail.setClientId(client1.getClientId());
            customClientDetail.setComment(client1.getComment());
            return "comment";
        }

            else {
                model.addAttribute("message", "Неверно введены данные");
            }

        return "index";
    }


}
