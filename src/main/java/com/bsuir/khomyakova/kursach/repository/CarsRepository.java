package com.bsuir.khomyakova.kursach.repository;

import com.bsuir.khomyakova.kursach.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.ui.Model;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car,Long> {

    @Query("SELECT u FROM Car u WHERE u.carName = ?1")
    Car findCarByName(String name);

    @Query("SELECT u FROM Car u WHERE u.carId = ?1")
    Car findCarById(Long id);

}
