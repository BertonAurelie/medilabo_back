package com.ocab.medilabopatient;

import com.ocab.medilabopatient.exception.MedilaboPatientException;
import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.mapper.PatientDtoMapper;
import com.ocab.medilabopatient.model.dto.request.PatientDto;
import com.ocab.medilabopatient.model.dto.request.PatientUpdatedDto;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;
import com.ocab.medilabopatient.repository.PatientRepository;
import com.ocab.medilabopatient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void givenPatientsList_whenGetPatientList_thenReturnAllPatients() {
        Date birthday = new Date();
        Patient patient1 = new Patient(1, "test1", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");
        Patient patient2 = new Patient(2, "test2", "test2", birthday, "M", "marc avenue adresse", "200-300-400", "test2@mail.com");

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);

        when(patientRepository.findAll()).thenReturn(patientList);

        List<Patient> result = patientService.getAllPatient();

        assertEquals(2, result.size());
    }

    @Test
    public void givenId_whenGetPatientInfo_ThenReturnPatientWithAgeDtoFoundWithId() {
        Date birthday = new Date();
        Patient patient1 = new Patient(1, "test1", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));

        PatientWithAgeDto patientWithAgeDto = patientService.getPatientInfo(1);

        assertEquals("test1", patientWithAgeDto.getFirstName());
        assertEquals("test1", patientWithAgeDto.getLastName());
        assertEquals(birthday, patientWithAgeDto.getBirthday());
        assertEquals("F", patientWithAgeDto.getGender());
        assertEquals("marc avenue adresse", patientWithAgeDto.getAddress());
        assertEquals("200-300-400", patientWithAgeDto.getPhoneNumber());
        assertEquals("test1@mail.com", patientWithAgeDto.getEmail());
        assertEquals(0, patientWithAgeDto.getAge());
        assertEquals(1, patientWithAgeDto.getId());
    }

    @Test
    public void givenId_whenGetPatientInfo_ThenReturnPatientNotFound() {
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        MedilaboPatientException exception = assertThrows(MedilaboPatientException.class, () -> patientService.getPatientInfo(1));

        assertEquals("patient not found", exception.getMessage());
    }

    @Test
    public void givenDto_whenAddPatient_thenReturnPatient() {
        Date birthday = new Date();
        PatientDto dto = new PatientDto();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setAddress("testAdresse");
        dto.setGender("M");
        dto.setBirthday(birthday);
        dto.setPhoneNumber("200-400-500");
        dto.setEmail("test@mail.com");

        Patient patient1 = new PatientDtoMapper().toEntity(dto);

        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);

        Patient result = patientService.addPatient(dto);

        assertEquals("test", result.getFirstName());
    }

    @Test
    public void givenPatientAlreadySaved_whenAddPatient_ThenReturnException() {
        Date birthday = new Date();
        PatientDto dto = new PatientDto();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setAddress("testAdresse");
        dto.setGender("M");
        dto.setBirthday(birthday);
        dto.setPhoneNumber("200-400-500");
        dto.setEmail("test@mail.com");

        Patient existing = new PatientDtoMapper().toEntity(dto);

        when(patientRepository.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(existing));

        MedilaboPatientException ex = assertThrows(
                MedilaboPatientException.class,
                () -> patientService.addPatient(dto)
        );

        assertEquals("patient already saved", ex.getMessage());
    }

    @Test
    public void givenPatientToUpdate_whenUpdatePatient_thenReturnUpdatedPatient() {
        Date birthday = new Date();
        PatientUpdatedDto patientUpdated = new PatientUpdatedDto(1, "update", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");
        Patient patientToUpdate = new Patient(1, "test1", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");

        when(patientRepository.findById(1)).thenReturn(Optional.of(patientToUpdate));

        Patient result = patientService.updatePatient(patientUpdated);

        assertEquals("update", result.getFirstName());
    }

    @Test
    public void givenPatientToUpdate_whenUpdatePatient_thenReturnExceptionPatientNotFound() {
        Date birthday = new Date();
        PatientUpdatedDto patientUpdated = new PatientUpdatedDto(1, "update", "test1", birthday, "F", "marc avenue adresse", "200-300-400", "test1@mail.com");

        when(patientRepository.findById(any())).thenReturn(Optional.empty());

        MedilaboPatientException exception = assertThrows(MedilaboPatientException.class, () -> patientService.updatePatient(patientUpdated));

        assertEquals("patient not found", exception.getMessage());
    }

    @Test
    public void givenId_whenDeletePatient_thenReturnSuccessfullyDeleted() {
        when(patientRepository.existsById(1)).thenReturn(true);

        boolean value = patientService.deletePatient(1);

        assertTrue(value);
    }

    @Test
    public void givenId_whenDeletePatient_thenReturnFailed() {
        when(patientRepository.existsById(1)).thenReturn(false);

        MedilaboPatientException exception = assertThrows(MedilaboPatientException.class, () -> patientService.deletePatient(1));

        assertEquals("patient unknown", exception.getMessage());
    }

}
