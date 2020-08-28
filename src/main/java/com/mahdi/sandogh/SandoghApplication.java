package com.mahdi.sandogh;

import com.ibm.icu.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class SandoghApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandoghApplication.class, args);
    }

    @PostConstruct
    void init() {
    }

}
