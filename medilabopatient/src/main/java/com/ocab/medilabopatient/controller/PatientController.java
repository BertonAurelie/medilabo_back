package com.ocab.medilabopatient.controller;

import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.request.PatientDto;
import com.ocab.medilabopatient.model.dto.request.PatientUpdatedDto;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;
import com.ocab.medilabopatient.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller Rest using PatientService to POST PUT GET AND DELETE Patient
 * add crossOrigin uri to communicate with front-end
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final static Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Get all patients in the database
     *
     * @return Patient's list
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllData() {
        logger.info("Executing getAllPatients");
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientWithAgeDto> getPatientData(@PathVariable int id) {
        logger.info("looking for patient info...");
        return new ResponseEntity<>(patientService.getPatientInfo(id), HttpStatus.OK);
    }

    /**
     * Add a new patient
     *
     * @param patient dto valid
     * @return patient added
     */
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid PatientDto patient) {
        logger.info("adding new patient..");
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    /**
     * Update an existed patient
     *
     * @param dto
     * @return patient updated
     */
    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientUpdatedDto dto) {
        logger.info("updating patient...");
        return new ResponseEntity<>(patientService.updatePatient(dto), HttpStatus.OK);
    }

    /**
     * Delete patient with id param
     *
     * @param id
     * @return true if patient deleted
     */
    @DeleteMapping
    public ResponseEntity<Void> deletePatient(@RequestParam int id) {
        logger.info("deleting patient...");
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
