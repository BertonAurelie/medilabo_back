package com.ocab.medilabopatient.repository;

import com.ocab.medilabopatient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findByLastName(String lastname);

    List<Patient> findByFirstName(String firstname);

    List<Patient> findByLastNameAndFirstName(String lastname, String firstname);


}
