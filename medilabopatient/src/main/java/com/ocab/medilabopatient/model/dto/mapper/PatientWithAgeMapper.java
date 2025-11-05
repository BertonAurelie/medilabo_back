package com.ocab.medilabopatient.model.dto.mapper;

import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;

public class PatientWithAgeMapper {

    public PatientWithAgeMapper() {
    }

    public PatientWithAgeDto toDto(Patient entity, int age) {
        PatientWithAgeDto dto = new PatientWithAgeDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBirthday(entity.getBirthday());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setAge(age);

        return dto;
    }
}
