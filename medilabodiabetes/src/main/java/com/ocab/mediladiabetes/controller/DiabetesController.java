package com.ocab.mediladiabetes.controller;

import com.ocab.mediladiabetes.service.DiabetesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * REST controller for diabetes risk evaluation.
 */
@RestController
@RequestMapping("/diabetes")
public class DiabetesController {

    /**
     * Service used to calculate diabetes risk.
     */
    private final DiabetesService diabetesService;

    /**
     * Constructor for DiabetesController.
     *
     * @param diabetesService diabetes service
     */
    public DiabetesController(DiabetesService diabetesService) {
        this.diabetesService = diabetesService;
    }

    /**
     * Get diabetes risk information for a patient.
     *
     * @param id patient id
     * @param age patient age
     * @param gender patient gender
     * @return diabetes risk level
     * @throws IOException exception during data processing
     */
    @GetMapping
    public ResponseEntity<String> getInfoToKnowRiskDiabetesPatient(
            @RequestParam int id,
            @RequestParam int age,
            @RequestParam String gender
    ) throws IOException {

        // Return calculated diabetes risk
        return new ResponseEntity<>(
                diabetesService.countOfDiabetesTriggersAndPatientAge(id, age, gender),
                HttpStatus.OK
        );
    }
}