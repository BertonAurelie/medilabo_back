package com.ocab.medilaboreport.service;

import com.ocab.medilaboreport.exception.MedilaboReportException;
import com.ocab.medilaboreport.model.Report;
import com.ocab.medilaboreport.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ReportService {
    private final ReportRepository reportRepository;
    Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReport(){
        logger.info("research all reports...");
        return reportRepository.findAll();
    }

    public List<Report> getAllReportOfThisPatient(int id){
        logger.info("research reports to this patient...");
        return reportRepository.findByPatientOrderByDateNoteDesc(id);
    }

    public Report addReport(Report report){
        if(report == null){
            throw new MedilaboReportException("this report is empty");
        }
        report.setDateNote(new Date());
        return reportRepository.save(report);
    }

    public void deleteAllReports(int id){
        logger.info("research report with this id patient....");
        reportRepository.deleteAllByPatient(id);
    }
}
