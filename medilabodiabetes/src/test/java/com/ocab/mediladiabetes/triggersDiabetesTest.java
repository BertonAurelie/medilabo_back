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
    public void givenString_whenResearchDiabetesTriggersOfPatient_thenReturnNumberOfTriggersDiabetesPatient() throws IOException {
        Date date = new Date();
        Report report = new Report("0",0,date,"je Poids et à la anormal du patient");
        Report report1 = new Report("01",0,date,"je Cholestérol et à la anormal du patient");
        List<Report> reports = new ArrayList<>();
        reports.add(report);
        reports.add(report1);

        when(reportService.getAllReportOfThisPatient(0)).thenReturn(reports);

        int result = diabetesService.researchDiabetesTriggersOfPatient(0);
        assertEquals(3, result);
    }

    @Test
    public void givenInfoPatient_whenResearchDiabetesTriggers_thenReturnChanceToHaveDiabetes() throws IOException {
        Date date = new Date();
        Report report = new Report("0",0,date,"je Poids et à la anormal du patient");
        Report report1 = new Report("01",0,date,"je Cholestérol et à la anormal du patient");
        List<Report> reports = new ArrayList<>();
        reports.add(report);
        reports.add(report1);

        when(reportService.getAllReportOfThisPatient(0)).thenReturn(reports);

        String result = diabetesService.countOfDiabetesTriggersAndPatientAge(0,31,"m");
        assertEquals("borderline", result);

    }


}
