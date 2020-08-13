package com.mahdi.sandogh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
public class SandoghApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandoghApplication.class, args);
    }

    @PostConstruct
    void init() {
//        TimeZone.setDefault(TimeZone.getDefault());
        System.out.println("Date in UTC: " + new Date().toString());

    }

}
