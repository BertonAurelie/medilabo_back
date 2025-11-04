package com.ocab.medilabopatient.controller;

import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;
import com.ocab.medilabopatient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patient")
public class patientController {
    private final static Logger logger = LoggerFactory.getLogger(patientController.class);
    private PatientService patientService;

    public patientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllData(){
        logger.info("Executing getAllPatients");
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientWithAgeDto> getPatientData(@PathVariable int id){
        logger.info("looking for patient info...");
        return new ResponseEntity<>(patientService.getPatientInfo(id), HttpStatus.OK);
    }


    /**
     * Add a new patient
     * @param patient
     * @return
     */
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        logger.info("adding new patient..");
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody  Patient patient){
        logger.info("updating patient...");
        return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePatient(@RequestParam int id) {
        logger.info("deleting patient...");
        patientService.deletePatient(id);
        return new ResponseEntity<>("patient deleted", HttpStatus.OK);
    }
}
