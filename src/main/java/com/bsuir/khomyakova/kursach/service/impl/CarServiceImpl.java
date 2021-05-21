package com.bsuir.khomyakova.kursach.service.impl;

import com.bsuir.khomyakova.kursach.model.Car;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import com.bsuir.khomyakova.kursach.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarServiceImpl(CarsRepository carsRepository) {
        this.carsRepository=carsRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carsRepository.findAll();
    }
}
