package com.ocab.medilabopatient.repository;

import com.ocab.medilabopatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByLastName(String lastname);

    List<Patient> findByFirstName(String firstname);

    List<Patient> findByLastNameAndFirstName(String lastname, String firstname);


}
