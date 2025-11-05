package com.ocab.medilabopatient.model.dto.mapper;

import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.request.PatientDto;


public class PatientDtoMapper {

    public Patient toEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setLastName(dto.getLastName());
        patient.setFirstName(dto.getFirstName());
        patient.setGender(dto.getGender());
        patient.setEmail(dto.getEmail());
        patient.setBirthday(dto.getBirthday());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());

        return patient;
    }
}
