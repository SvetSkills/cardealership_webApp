package com.bsuir.khomyakova.kursach.controller;

import com.bsuir.khomyakova.kursach.model.Car;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class RegistrationControllerTest {

@Autowired
private CarsRepository carsRepository;

    @Test
    public void startPage() {
        List<Car> list1 = carsRepository.findAll();
        System.out.println(list1.get(0).getCarId());

    }

    @Test
    void login() {
    }
}