package com.ocab.mediladiabetes.service;

import com.ocab.medilaboreport.model.Report;
import com.ocab.medilaboreport.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiabetesService {
    Logger logger = LoggerFactory.getLogger(DiabetesService.class);
    private ReportService reportService;

    public DiabetesService(ReportService reportService) {
        this.reportService = reportService;
    }

    public String countOfDiabetesTriggersAndPatientAge(int id, int age, String gender) throws IOException {
        String trigger = "none";
        int count = researchDiabetesTriggersOfPatient(id);

        if (count < 1) {
            trigger = "none";
        }

        if (age > 30) {
            if (count >= 2 && count <= 5) {
                trigger = "borderline";
            } else if (count == 6 || count == 7) {
                trigger = "in danger";
            } else if (count >= 8) {
                trigger = "early onset";
            }
        }

        if (age < 30) {
            if (gender.equalsIgnoreCase("m")) {
                if (count >= 3) {
                    trigger = "in danger";
                }
                if (count >= 5) {
                    trigger = "early onset";
                }
            }
            if (gender.equalsIgnoreCase("f")) {
                if (count >= 4) {
                    trigger = "in danger";
                }

                if (count >= 7) {
                    trigger = "early onset";
                }
            }
        }
        logger.info("trigger : {}", trigger);
        return trigger;
    }

    public int researchDiabetesTriggersOfPatient(int id) throws IOException {
        Resource resource = new ClassPathResource("static/triggersDiabetes.txt");

        BufferedReader triggersDiabetesList = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        int count = 0;

        List<String> line = new ArrayList<>(triggersDiabetesList.lines().map(String::toLowerCase).toList());

        List<Report> reportsOfPatient = reportService.getAllReportOfThisPatient(id);

        for (Report report : reportsOfPatient) {
            String[] patientReport = report.getNote().split(" ");
            String previousCleaned = null;
            for (String word : patientReport) {
                String cleaned = word.toLowerCase().replaceAll("[^a-zA-Z0-9àâçéèêëîïôûùüÿñæœ]", "");
                logger.info("wordTest : {}", word);

                if (previousCleaned != null && (previousCleaned.equals("hémoglobine")) && cleaned.equals("a1c")) {
                    count++;
                    logger.info("Trigger détecté : Hémoglobine A1C");
                    previousCleaned = cleaned;
                    continue;
                }

                if (line.contains(cleaned)) {
                    count++;
                    logger.info("word : {}", word);
                    line.remove(cleaned);
                }
                previousCleaned = cleaned;
            }
        }

        logger.info("count : {}", count);
        return count;
    }
}
