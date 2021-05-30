package com.bsuir.khomyakova.kursach.repository;

import com.bsuir.khomyakova.kursach.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    @Query("SELECT u FROM AppUser u WHERE u.email=?1")
    AppUser findAppUserByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.userId=?1")
    AppUser findAppUserById(Long id);

}
