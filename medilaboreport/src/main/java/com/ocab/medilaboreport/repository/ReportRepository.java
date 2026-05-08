package com.ocab.medilaboreport.repository;

import com.ocab.medilaboreport.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Report entities in MongoDB.
 */
@Repository
public interface ReportRepository extends MongoRepository<Report, String> {

    /**
     * Find all reports for a specific patient.
     *
     * @param id patient id
     * @return list of reports
     */
    List<Report> findByPatient(int id);

    /**
     * Find all reports for a specific patient
     * ordered by date in descending order.
     *
     * @param id patient id
     * @return sorted list of reports
     */
    List<Report> findByPatientOrderByDateNoteDesc(int id);

    /**
     * Delete all reports for a specific patient.
     *
     * @param id patient id
     */
    void deleteAllByPatient(int id);
}