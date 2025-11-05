package com.ocab.medilabopatient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.mapper.PatientDtoMapper;
import com.ocab.medilabopatient.model.dto.request.PatientDto;
import com.ocab.medilabopatient.model.dto.request.PatientUpdatedDto;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;
import com.ocab.medilabopatient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PatientService patientService;

    @Test
    public void givenAllPatient() throws Exception {
        Date birthday = new Date();
        Patient patient1 = new Patient(1, "test1", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");
        Patient patient2 = new Patient(2, "test2", "test2", birthday, "M", "marc avenue adresse", "200-300-400", "test2@mail.com");

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);

        when(patientService.getAllPatient()).thenReturn(patientList);

        this.mockMvc.perform(get("/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void getPatientData() throws Exception {
        Date birthday = new Date();
        PatientWithAgeDto dto = new PatientWithAgeDto();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setAddress("testAdresse");
        dto.setGender("M");
        dto.setBirthday(birthday);
        dto.setPhoneNumber("200-400-500");
        dto.setEmail("test@mail.com");
        dto.setId(1);

        when(patientService.getPatientInfo(1)).thenReturn(dto);

        this.mockMvc.perform(get("/patient/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAdresse"));
    }

    @Test
    public void addPatient() throws Exception {
        Date birthday = new Date();
        PatientDto dto = new PatientDto();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setAddress("testAdresse");
        dto.setGender("M");
        dto.setBirthday(birthday);
        dto.setPhoneNumber("200-400-5000");
        dto.setEmail("test@mail.com");

        Patient patient = new PatientDtoMapper().toEntity(dto);

        when(patientService.addPatient(any(PatientDto.class))).thenReturn(patient);

        mockMvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAdresse"));
    }

    @Test
    public void updatePatient() throws Exception {
        Date birthday = new Date();
        Patient patientUpdated = new Patient(1, "update", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");
        Patient patientToUpdate = new Patient(1, "test1", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");


        when(patientService.updatePatient(any(PatientUpdatedDto.class))).thenReturn(patientUpdated);

        mockMvc.perform(put("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("update"));
    }

    @Test
    public void deletePatient() throws Exception {
        when(patientService.deletePatient(1)).thenReturn(true);

        mockMvc.perform(delete("/patient")
                        .param("id", "1"))
                .andExpect(status().isNoContent());
    }
}
