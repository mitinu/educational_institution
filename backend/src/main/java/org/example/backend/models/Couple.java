package org.example.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Couple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String office;
    private String academicSubject;
    private String professor;

    public Couple() { }
    public Couple(String office, String academicSubject, String professor) {
        this.office = office;
        this.academicSubject = academicSubject;
        this.professor = professor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOffice() { return office; }
    public void setOffice(String office) { this.office = office; }

    public String getAcademicSubject() { return academicSubject; }
    public void setAcademicSubject(String academicSubject) { this.academicSubject = academicSubject; }

    public String getProfessor() { return professor; }
    public void setProfessor(String professor) { this.professor = professor; }
}
