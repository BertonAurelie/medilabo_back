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

/**
 * Service used to evaluate diabetes risk.
 */
@Service
public class DiabetesService {

    /**
     * Logger for this service.
     */
    Logger logger = LoggerFactory.getLogger(DiabetesService.class);

    /**
     * Service used to access patient reports.
     */
    private ReportService reportService;

    /**
     * Constructor for DiabetesService.
     *
     * @param reportService report service
     */
    public DiabetesService(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Calculate diabetes risk according to
     * patient age, gender, and trigger count.
     *
     * @param id patient id
     * @param age patient age
     * @param gender patient gender
     * @return diabetes risk level
     * @throws IOException exception while reading trigger file
     */
    public String countOfDiabetesTriggersAndPatientAge(
            int id,
            int age,
            String gender
    ) throws IOException {

        String trigger = "none";

        // Count detected diabetes triggers
        int count = researchDiabetesTriggersOfPatient(id);

        if (count < 1) {
            trigger = "none";
        }

        // Risk calculation for patients older than 30
        if (age > 30) {

            if (count >= 2 && count <= 5) {
                trigger = "borderline";

            } else if (count == 6 || count == 7) {
                trigger = "in danger";

            } else if (count >= 8) {
                trigger = "early onset";
            }
        }

        // Risk calculation for patients younger than 30
        if (age < 30) {

            // Male patient rules
            if (gender.equalsIgnoreCase("m")) {

                if (count >= 3) {
                    trigger = "in danger";
                }

                if (count >= 5) {
                    trigger = "early onset";
                }
            }

            // Female patient rules
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

    /**
     * Search and count diabetes trigger words
     * inside patient reports.
     *
     * @param id patient id
     * @return number of detected triggers
     * @throws IOException exception while reading trigger file
     */
    public int researchDiabetesTriggersOfPatient(int id) throws IOException {

        // Load trigger words file
        Resource resource = new ClassPathResource("static/triggersDiabetes.txt");

        BufferedReader triggersDiabetesList = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
        );

        int count = 0;

        // Convert trigger words to lowercase list
        List<String> line = new ArrayList<>(
                triggersDiabetesList.lines()
                        .map(String::toLowerCase)
                        .toList()
        );

        // Get all reports for the patient
        List<Report> reportsOfPatient = reportService.getAllReportOfThisPatient(id);

        for (Report report : reportsOfPatient) {

            // Split report text into words
            String[] patientReport = report.getNote().split(" ");

            String previousCleaned = null;

            for (String word : patientReport) {

                // Clean word from special characters
                String cleaned = word.toLowerCase()
                        .replaceAll("[^a-zA-Z0-9àâçéèêëîïôûùüÿñæœ]", "");

                logger.info("wordTest : {}", word);

                // Special case for "Hémoglobine A1C"
                if (previousCleaned != null
                        && previousCleaned.equals("hémoglobine")
                        && cleaned.equals("a1c")) {

                    count++;

                    logger.info("Trigger detected : Hémoglobine A1C");

                    previousCleaned = cleaned;

                    continue;
                }

                // Check if word is a diabetes trigger
                if (line.contains(cleaned)) {

                    count++;

                    logger.info("word : {}", word);

                    // Remove trigger to avoid duplicate count
                    line.remove(cleaned);
                }

                previousCleaned = cleaned;
            }
        }

        logger.info("count : {}", count);

        return count;
    }
}