package com.bsuir.khomyakova.kursach.service.impl;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bsuir.khomyakova.kursach.model.AppUser;
import com.bsuir.khomyakova.kursach.model.AppUserRole;
import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

//    @Override
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//        return appUserRepository.findAppUserByEmail(email);
//    }

    public List<AppUser> listAll(){
        return appUserRepository.findAll();
    }

    public AppUser getByEmail(String email)
    {
        return appUserRepository.findAppUserByEmail(email);

    }

    public AppUser get(Long id){
        return appUserRepository.findAppUserById(id);
    }

    public void save(AppUser user){
        appUserRepository.save(user);
    }

    public void delete(Long id)
    {
        appUserRepository.deleteById(id);

    }


}





















    //private final templates String USER_NOT_FOUNT_MSG = "User with email %s not found";
    /*private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = appUserRepository.findAppUserByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("Пользователь с таким логином не найден!");
        }
       //return appUserRepository.findAppUserByEmail(email);

                   // return new UsernameNotFoundException(String.format(USER_NOT_FOUNT_MSG, email));
//return new AppUserDetails();
        return appUserRepository.findAppUserByEmail(email);
       // return (UserDetails) user;
       }



    public String signUpUser(AppUser appUser){
       boolean userExists = appUserRepository.
               findAppUserByEmail(appUser.getEmail())
               .isPresent();

       if (userExists){
           throw new IllegalStateException("Аккаунт с таким email уже существует!");
       }
       String encodedPassword =  bCryptPasswordEncoder
               .encode(appUser.getPassword());
       appUser.setPassword(encodedPassword);

       appUserRepository.save(appUser);

      return "";
    }
     }*/