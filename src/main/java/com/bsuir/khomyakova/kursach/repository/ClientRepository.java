package com.bsuir.khomyakova.kursach.repository;

import com.bsuir.khomyakova.kursach.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Query("SELECT h FROM Client h WHERE h.name=?1")
   // AppUser findAppUserByEmail(String email);
    Client findClientById(String name);
 //   Client save(Client client);
  //  AppUser save(AppUser user);
}
