package com.ocab.medilabopatient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MedilabopatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedilabopatientApplication.class, args);
    }
}
