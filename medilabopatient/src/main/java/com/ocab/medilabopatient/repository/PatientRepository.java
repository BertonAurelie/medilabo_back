package com.ocab.medilabopatient.repository;

import com.ocab.medilabopatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Patient entities.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * Find a patient by email address.
     *
     * @param email patient email address
     * @return optional patient
     */
    Optional<Patient> findByEmail(String email);
}