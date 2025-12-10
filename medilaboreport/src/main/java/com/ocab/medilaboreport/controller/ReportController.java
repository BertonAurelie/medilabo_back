package com.ocab.medilaboreport.controller;

import com.ocab.medilaboreport.model.Report;
import com.ocab.medilaboreport.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/report")
public class ReportController {
    public ReportService reportService;
    Logger logger = LoggerFactory.getLogger(ReportController.class);

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports(){
        logger.info("research....");
        return new ResponseEntity<>(reportService.getAllReport(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Report>> getReportPatientWithId(@PathVariable int id){
        logger.info("report with id... ");
        return new ResponseEntity<>(reportService.getAllReportOfThisPatient(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Report> addnewReport(@RequestBody Report report){
        logger.info("add new report....");
        return new ResponseEntity<>(reportService.addReport(report), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteReports(@RequestParam int id){
        logger.info("connection to delete reports...");
        reportService.deleteAllReports(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
