package com.ocab.medilabogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@SpringBootApplication()
public class MedilaboApplication {
    private static final String PATH_PATIENT_URI = "http://patient:8081";
    private static final String PATH_REPORT_URI = "http://report:8082";
    private static final String PATH_DIABETES_RISKS_URI = "http://diabetes:8083";

    @Bean
    public RouterFunction<ServerResponse> patientRoutes() {
        return route("list_of_patients")
                .GET("/patient", http())
                .before(uri(PATH_PATIENT_URI))
                .build()
                .and(route("get_one_patient")
                        .GET("/patient/{id}", http())
                        .before(uri(PATH_PATIENT_URI))
                        .build())
                .and(route("add_new_patient")
                        .POST("/patient/add", http())
                        .before(uri(PATH_PATIENT_URI))
                        .build())
                .and(route("update_patient")
                        .PUT("/patient", http())
                        .before(uri(PATH_PATIENT_URI))
                        .build())
                .and(route("patient_delete")
                        .DELETE("/patient/delete/{id}", http())
                        .before(uri(PATH_PATIENT_URI))
                        .build());
    }

    @Bean
    public RouterFunction<ServerResponse> reportRoutes() {
        return route("list_of_reports")
                .GET("/report", http())
                .before(uri(PATH_REPORT_URI))
                .build()
                .and(route("report_one_patient")
                        .GET("/report/{id}", http())
                        .before(uri(PATH_REPORT_URI))
                        .build())
                .and(route("post_report")
                        .POST("/report", http())
                        .before(uri(PATH_REPORT_URI))
                        .build()
                );
    }

    @Bean
    public RouterFunction<ServerResponse> diabetesRiskRoutes() {
        return route("risks_diabetes")
                .GET("/diabetes", http())
                .before(uri(PATH_DIABETES_RISKS_URI))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(MedilaboApplication.class, args);
    }
}
