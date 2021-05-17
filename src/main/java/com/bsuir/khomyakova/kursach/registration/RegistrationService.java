package com.bsuir.khomyakova.kursach.registration;

import com.bsuir.khomyakova.kursach.model.AppUser;
import com.bsuir.khomyakova.kursach.model.AppUserRole;
import com.bsuir.khomyakova.kursach.model.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {

       boolean isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail){
           throw new IllegalStateException("Email не верный!!");
       }

        return appUserService.signUpUser(
                new AppUser(
                        request.getPassword(),
                        request.getEmail(),
                        AppUserRole.USER
                ));
    }
}
