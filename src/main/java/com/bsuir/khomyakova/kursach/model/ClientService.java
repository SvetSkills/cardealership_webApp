package com.bsuir.khomyakova.kursach.model;

import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import com.bsuir.khomyakova.kursach.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements  UserDetailsService{

        //private final static String USER_NOT_FOUNT_MSG = "User with email %s not found";
        private final ClientRepository clientRepository;

        public ClientService(ClientRepository clientRepository){
            this.clientRepository=clientRepository;
        }

    @Override
        public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

            Client client = clientRepository.findClientById(name);
            if(client==null){
                throw new UsernameNotFoundException("Клиент с таким айди не найден!");
            }

        return new ClientDetails(client);

        }

    }
//return appUserRepository.findAppUserByEmail(email);
// return new UsernameNotFoundException(String.format(USER_NOT_FOUNT_MSG, email));
/*return new AppUserDetails();*/
//return clientRepository.findClientById(clientId); // return (UserDetails) user;