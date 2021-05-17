package com.bsuir.khomyakova.kursach.model;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findAppUserByEmail(email);
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                //.findByEmail(appUser.getEmail())
                .findAppUserByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
        return appUser.getEmail();
    }

}





















    //private final static String USER_NOT_FOUNT_MSG = "User with email %s not found";
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