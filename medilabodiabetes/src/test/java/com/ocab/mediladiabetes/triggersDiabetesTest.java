package com.ocab.mediladiabetes;

import com.ocab.medilaboreport.model.Report;
import com.ocab.medilaboreport.service.ReportService;
import com.ocab.mediladiabetes.service.DiabetesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class triggersDiabetesTest {
    @InjectMocks
    private DiabetesService diabetesService;

    @Mock
    private ReportService reportService;

    @Test
    public void givenInfoPatient_whenResearchDiabetesTriggers_thenReturnNone() throws IOException {
        Date date = new Date();
        Report report = new Report("0", 0, date, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé");
        List<Report> reports = new ArrayList<>();
        reports.add(report);

        when(reportService.getAllReportOfThisPatient(0)).thenReturn(reports);

        String result = diabetesService.countOfDiabetesTriggersAndPatientAge(0, 59, "f");
        assertEquals("none", result);
    }

    @Test
    public void givenInfoPatient_whenResearchDiabetesTriggers_thenReturnBorderline() throws IOException {
        Date date = new Date();
        Report report = new Report("0", 0, date, "Le patient déclare qu'il ressent beaucoup de stress au travail il se plaint également que son audition est anormale dernièrement");
        Report report1 = new Report("0", 0, date, "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois il remarque également que son audition continue d'être anormale");
        List<Report> reports = new ArrayList<>();
        reports.add(report);
        reports.add(report1);

        when(reportService.getAllReportOfThisPatient(0)).thenReturn(reports);

        String result = diabetesService.countOfDiabetesTriggersAndPatientAge(0, 80, "m");
        assertEquals("borderline", result);
    }

    @Test
    public void givenInfoPatient_whenResearchDiabetesTriggers_thenReturnInDanger() throws IOException {
        Date date = new Date();
        Report report = new Report("0", 0, date, "Le patient déclare qu'il fume depuis peu");
        Report report1 = new Report("0", 0, date, "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière il se plait également de crises d'apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
        List<Report> reports = new ArrayList<>();
        reports.add(report);
        reports.add(report1);

        when(reportService.getAllReportOfThisPatient(0)).thenReturn(reports);

        String result = diabetesService.countOfDiabetesTriggersAndPatientAge(0, 21, "m");
        assertEquals("in danger", result);
    }

    @Test
    public void givenInfoPatient_whenResearchDiabetesTriggers_thenReturnEarlyOnSet() throws IOException {
        String note1 = "Le patient déclare qu'il lui est devenu difficile de monter les escaliers il se plaint également d'être essouflé Tests de laboratoire indiquant que les anticorps sont élevés Reaction aux médicaments";
        String note2 = "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps";
        String note3 = "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé";
        String note4 = "Taille, Poids, Cholestérol, Vertige et Réaction";
        Date date = new Date();
        Report report1 = new Report("0", 0, date, note1);
        Report report2 = new Report("0", 0, date, note2);
        Report report3 = new Report("0", 0, date, note3);
        Report report4 = new Report("0", 0, date, note4);
        List<Report> reports = new ArrayList<>();
        reports.add(report1);
        reports.add(report2);
        reports.add(report3);
        reports.add(report4);

        when(reportService.getAllReportOfThisPatient(0)).thenReturn(reports);

        String result = diabetesService.countOfDiabetesTriggersAndPatientAge(0, 23, "f");
        assertEquals("early onset", result);
    }
}
