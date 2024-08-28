package com.sparta.schedule2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Schedule2Application {

    public static void main(String[] args) {
        SpringApplication.run(Schedule2Application.class, args);
    }

}
