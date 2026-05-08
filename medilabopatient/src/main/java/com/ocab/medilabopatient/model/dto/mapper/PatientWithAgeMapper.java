package com.ocab.medilabopatient.model.dto.mapper;

import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;

/**
 * Mapper class used to convert Patient entities
 * into PatientWithAgeDto objects.
 */
public class PatientWithAgeMapper {

    /**
     * Default constructor.
     */
    public PatientWithAgeMapper() {
    }

    /**
     * Convert a Patient entity into a PatientWithAgeDto.
     *
     * @param entity patient entity
     * @param age calculated patient age
     * @return PatientWithAgeDto object
     */
    public PatientWithAgeDto toDto(Patient entity, int age) {

        // Create a new DTO object
        PatientWithAgeDto dto = new PatientWithAgeDto();

        // Map entity fields to DTO fields
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBirthday(entity.getBirthday());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());

        // Set calculated age
        dto.setAge(age);

        return dto;
    }
}