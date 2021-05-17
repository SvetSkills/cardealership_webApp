package com.bsuir.khomyakova.kursach.controller;
import com.bsuir.khomyakova.kursach.registration.RegistrationRequest;
import com.bsuir.khomyakova.kursach.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/" +
        "/v1/registration")
@AllArgsConstructor
public class AppUserRegistrationController {

    private RegistrationService registrationService;

    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}

