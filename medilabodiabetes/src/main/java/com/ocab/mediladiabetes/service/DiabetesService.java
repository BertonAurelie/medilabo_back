package com.ocab.mediladiabetes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DiabetesService {

    public int researchDiabetesTriggersOfPatient(List<String> reports) throws IOException {
        BufferedReader triggersDiabetesList = Files.newBufferedReader(Paths.get("src/main/resources/static/triggersDiabetes.txt"));
        int count = 0;

        List<String> line = new ArrayList<>(triggersDiabetesList.lines().map(String::toLowerCase).toList());

        for (String report : reports) {
            String[] patientReport = report.split(" ");
            for (String word : patientReport) {
                if (line.contains(word.toLowerCase())) {
                    count++;
                    line.remove(word.toLowerCase());
                }
            }
        }
        return count;
    }
}
