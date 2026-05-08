package com.ocab.medilaboreport.controller;

import com.ocab.medilaboreport.model.Report;
import com.ocab.medilaboreport.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing reports.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Constructor for ReportController.
     *
     * @param reportService service used to manage reports
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Get all reports.
     *
     * @return list of all reports
     */
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        logger.info("Getting all reports...");
        return new ResponseEntity<>(reportService.getAllReport(), HttpStatus.OK);
    }

    /**
     * Get all reports for a specific patient.
     *
     * @param id patient id
     * @return list of reports for the patient
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Report>> getReportPatientWithId(@PathVariable int id) {
        logger.info("Getting reports for patient id: {}", id);
        return new ResponseEntity<>(reportService.getAllReportOfThisPatient(id), HttpStatus.OK);
    }

    /**
     * Add a new report.
     *
     * @param report report to add
     * @return created report
     */
    @PostMapping
    public ResponseEntity<Report> addnewReport(@RequestBody Report report) {
        logger.info("Adding new report...");
        return new ResponseEntity<>(reportService.addReport(report), HttpStatus.CREATED);
    }

    /**
     * Delete all reports for a patient.
     *
     * @param id patient id
     * @return no content response
     */
    @DeleteMapping()
    public ResponseEntity<Void> deleteReports(@RequestParam int id) {
        logger.info("Deleting reports for patient id: {}", id);
        reportService.deleteAllReports(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}