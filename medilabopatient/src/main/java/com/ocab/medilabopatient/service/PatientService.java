package com.ocab.medilabopatient.service;

import com.ocab.medilabopatient.exception.MedilaboPatientException;
import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.mapper.PatientDtoMapper;
import com.ocab.medilabopatient.model.dto.mapper.PatientWithAgeMapper;
import com.ocab.medilabopatient.model.dto.request.PatientDto;
import com.ocab.medilabopatient.model.dto.request.PatientUpdatedDto;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;
import com.ocab.medilabopatient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Liste de patients
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public PatientWithAgeDto getPatientInfo(int id) {
        PatientWithAgeDto patientToDto;
        int age;
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            Patient optionalToPatient = patient.get();
            age = getAge(patient.get().getBirthday());
            logger.info(String.valueOf(age));

            patientToDto = new PatientWithAgeMapper().toDto(optionalToPatient, age);

        } else {
            throw new MedilaboPatientException("patient not found", HttpStatus.NOT_FOUND);
        }

        return patientToDto;
    }

    //Ajout d'un patient
    public Patient addPatient(PatientDto dto) {
        Patient patient = new PatientDtoMapper().toEntity(dto);

        if (patientRepository.findByEmail(patient.getEmail()).isEmpty()) {
            logger.info("patient inconnu, enregistrement en cours");
            patient.setPassword(passwordEncoder.encode(dto.getPassword()));
            patient = patientRepository.save(patient);
        } else {
            logger.info("patient déjà enregistré");
            throw new MedilaboPatientException("patient already saved", HttpStatus.OK);
        }
        return patient;
    }

    //modification d'un patient
    public Patient updatePatient(PatientUpdatedDto dto) {
        Optional<Patient> optionalPatient = patientRepository.findById(dto.getId());

        if (optionalPatient.isEmpty()) {
            logger.info("patient introuvable");
            throw new MedilaboPatientException("patient not found", HttpStatus.NOT_FOUND);
        }
        Patient patientToUpdate = optionalPatient.get();

        if (dto.getLastName() != null) {
            patientToUpdate.setLastName(dto.getLastName());
        }
        if (dto.getFirstName() != null) {
            patientToUpdate.setFirstName(dto.getFirstName());
        }

        if (dto.getBirthday() != null) {
            patientToUpdate.setBirthday(dto.getBirthday());
        }

        if (dto.getAddress() != null) {
            patientToUpdate.setAddress(dto.getAddress());
        }

        if (dto.getGender() != null) {
            patientToUpdate.setGender(dto.getGender());
        }

        if (dto.getEmail() != null) {
            patientToUpdate.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null) {
            patientToUpdate.setPhoneNumber(dto.getPhoneNumber());
        }

        logger.info("sauvegarde du patient modifié");

        patientRepository.save(patientToUpdate);

        return patientToUpdate;
    }

    //suppression d'un patient
    public Boolean deletePatient(int id) {
        if (patientRepository.existsById(id)) {
            logger.info("suppression de l'utilisateur");
            patientRepository.deleteById(id);
        } else {
            logger.info("utilisateur inconnu");
            throw new MedilaboPatientException("patient unknown", HttpStatus.NOT_FOUND);
        }

        return true;
    }

    private int getAge(Date birthday) {
        LocalDate birthdayLocalDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodBetween = Period.between(birthdayLocalDate, LocalDate.now());

        return periodBetween.getYears();
    }
}
