package com.ocab.medilabopatient.model.dto.mapper;

import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.request.PatientDto;

/**
 * Mapper class used to convert PatientDto objects
 * into Patient entities.
 */
public class PatientDtoMapper {

    /**
     * Convert a PatientDto into a Patient entity.
     *
     * @param dto patient DTO object
     * @return Patient entity
     */
    public Patient toEntity(PatientDto dto) {

        // Create a new Patient object
        Patient patient = new Patient();

        // Map DTO fields to entity fields
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