package com.bsuir.khomyakova.kursach;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
//@RestController
public class KursachApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(KursachApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(KursachApplication.class);
    }
}
