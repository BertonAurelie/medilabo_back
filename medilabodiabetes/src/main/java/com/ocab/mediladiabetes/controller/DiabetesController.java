package com.ocab.mediladiabetes.controller;

import com.ocab.mediladiabetes.service.DiabetesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/diabetes")
public class DiabetesController {
    private final DiabetesService diabetesService;

    public DiabetesController(DiabetesService diabetesService) {
        this.diabetesService = diabetesService;
    }

    @GetMapping
    public ResponseEntity<String> getInfoToKnowRiskDiabetesPatient(@RequestParam int id, @RequestParam int age, @RequestParam String gender) throws IOException {
        return new ResponseEntity<>(diabetesService.countOfDiabetesTriggersAndPatientAge(id, age, gender), HttpStatus.OK);
    }
}
