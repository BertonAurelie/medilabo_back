package com.ocab.mediladiabetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.ocab.mediladiabetes",
				"com.ocab.medilaboreport"
		}
)
public class MediladiabetesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediladiabetesApplication.class, args);
	}

}
