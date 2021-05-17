package com.bsuir.khomyakova.kursach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KursachApplication {

    public static void main(String[] args) {

        SpringApplication.run(KursachApplication.class, args);
    }
}
