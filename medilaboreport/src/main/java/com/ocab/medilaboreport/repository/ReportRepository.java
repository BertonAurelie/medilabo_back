package com.ocab.medilaboreport.repository;

import com.ocab.medilaboreport.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByPatient(int id);
    List<Report> findByPatientOrderByDateNoteDesc(int id);

    void deleteAllByPatient(int id);
}
