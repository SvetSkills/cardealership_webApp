package com.bsuir.khomyakova.kursach.repository;

import com.bsuir.khomyakova.kursach.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("SELECT h FROM Client h WHERE h.name=?1")
    Client findClientByName(String name);

    @Query("SELECT h FROM Client h WHERE h.clientId=?1")
    Client findClientById(Long id);
}
