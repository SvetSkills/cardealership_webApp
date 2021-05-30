package com.bsuir.khomyakova.kursach.service.impl;

import com.bsuir.khomyakova.kursach.model.Car;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import com.bsuir.khomyakova.kursach.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarServiceImpl /*implements CarService*/{

    @Autowired
    private CarsRepository carsRepository;

     public List<Car> listAll(){
          return carsRepository.findAll();
     }
     public void save(Car cars){
         carsRepository.save(cars);
     }
     public Car get(Long id){
         return carsRepository.findById(id).get();
     }
}
