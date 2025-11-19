package com.ocab.mediladiabetes;

import com.ocab.mediladiabetes.service.DiabetesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class triggersDiabetesTest {
    @InjectMocks
    private DiabetesService diabetesService;

    @Test
    public void givenString_whenResearchDiabetesTriggersOfPatient_thenReturnNumberOfTriggersDiabetesPatient() throws IOException {
        String report0 = "Je Poids et à la anormal du patient";
        String report1 = "Poids et à la Taille";
        List<String> reports = new ArrayList<>();
        reports.add(report0);
        reports.add(report1);

        int result = diabetesService.researchDiabetesTriggersOfPatient(reports);
        assertEquals(3, result);
    }


}
