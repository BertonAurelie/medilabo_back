package com.ocab.medilaboreport.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection="report")
public class Report {
    @Id
    private String id;
    private int patient;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateNote;
    private String note;

    public Report() {
    }

    public Report(String id, int patient, Date dateNote, String note) {
        this.id = id;
        this.patient = patient;
        this.dateNote = dateNote;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
