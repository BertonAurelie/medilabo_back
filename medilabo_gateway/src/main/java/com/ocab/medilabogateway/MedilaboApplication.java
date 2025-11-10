package com.ocab.medilabogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.*;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@SpringBootApplication
public class MedilaboApplication {
	private static final String PATH_PATIENT_URI = "http://localhost:8081";
	@Bean
	public RouterFunction<ServerResponse> myRoutes() {
		return route("list_of_patients")
					.GET("/patient", http())
					.before(uri(PATH_PATIENT_URI))
					.build()
				.and(route("patient_delete")
						.GET("/patient/{id}", http())
						.before(uri(PATH_PATIENT_URI))
						.build())
				.and(route("add_new_patient")
						.POST("/patient", http())
						.before(uri(PATH_PATIENT_URI))
						.build())
				.and(route("patient_delete")
					.DELETE("/patient/delete/{id}", http())
					.before(uri(PATH_PATIENT_URI))
					.build()
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(MedilaboApplication.class, args);
	}
}
