package com.ocab.medilabopatient.service;

import com.ocab.medilabopatient.exception.MedilaboPatientException;
import com.ocab.medilabopatient.model.Patient;
import com.ocab.medilabopatient.model.dto.mapper.PatientDtoMapper;
import com.ocab.medilabopatient.model.dto.mapper.PatientWithAgeMapper;
import com.ocab.medilabopatient.model.dto.request.PatientDto;
import com.ocab.medilabopatient.model.dto.response.PatientWithAgeDto;
import com.ocab.medilabopatient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //Liste de patients
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public PatientWithAgeDto getPatientInfo(int id) {
        PatientWithAgeDto patientToDto = new PatientWithAgeDto();
        int age;
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            Patient optionalToPatient = patient.get();
            age = getAge(patient.get().getBirthday());
            logger.info(String.valueOf(age));
            
            patientToDto = new PatientWithAgeMapper().toDto(optionalToPatient, age);

        }
        return patientToDto ;
    }

    //Ajout d'un patient
    public Patient addPatient(PatientDto dto) {
        Patient patient = new PatientDtoMapper().toEntity(dto);

        if (patientRepository.findByLastNameAndFirstName(patient.getLastName(), patient.getFirstName()).isEmpty()) {
            logger.info("patient inconnu, enregistrement en cours");
            patientRepository.save(patient);
        } else {
            logger.info("patient déjà enregistré");
            throw new MedilaboPatientException("patient already saved ");
        }
        return patient;
    }

    //modification d'un patient
    public Patient updatePatient(Patient patient) {
        logger.info(patient.toString());
        Optional<Patient> optionalPatient = patientRepository.findById(patient.getId());

        if (optionalPatient.isPresent()) {
            Patient patientToUpdate = optionalPatient.get();

            if(patient.getLastName() != null){
                patientToUpdate.setLastName(patient.getLastName());
            }
            if(patient.getFirstName() != null){
                patientToUpdate.setFirstName(patient.getFirstName());
            }

            if(patient.getBirthday() != null){
                patientToUpdate.setBirthday(patient.getBirthday());
            }

            if(patient.getAddress() != null){
                patientToUpdate.setAddress(patient.getAddress());
            }

            if(patient.getGender() != null){
                patientToUpdate.setGender(patient.getGender());
            }

            if(patient.getEmail() != null){
                patientToUpdate.setEmail(patient.getEmail());
            }

            logger.info("sauvegarde du patient modifié");
            patientRepository.save(patientToUpdate);
        } else {
            logger.info("patient introuvable");
        }

        return patient;
    }

    //suppression d'un patient
    public void deletePatient(int id) {
        if (patientRepository.existsById(id)) {
            logger.info("suppression de l'utilisateur");
            patientRepository.deleteById(id);
        } else {
            logger.info("utilisateur inconnu");
        }
    }

    private int getAge(Date birthday) {
        LocalDate birthdayLocalDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodBetween = Period.between(birthdayLocalDate, LocalDate.now());

        return periodBetween.getYears();
    }
}
