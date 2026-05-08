package com.ocab.medilabopatient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for password encoding.
 */
@Configuration
public class PasswordConfig {

    /**
     * Create a PasswordEncoder bean using BCrypt algorithm.
     *
     * @return password encoder
     */
    @Bean
    public PasswordEncoder encoder() {

        // BCrypt encoder with strength level 12
        return new BCryptPasswordEncoder(12);
    }
}