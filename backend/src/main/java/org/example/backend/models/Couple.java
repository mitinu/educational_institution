package org.example.backend.models;

import jakarta.persistence.*;


@Entity
public class Couple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private String office;
    private String academicSubject;
    private String professor;
    private String studyGroup;

    @ManyToOne
    @JoinColumn(name = "Schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    public Couple() { }
    public Couple(String office, String academicSubject, String professor, String studyGroup, Schedule schedule, int number) {
        this.office = office;
        this.academicSubject = academicSubject;
        this.professor = professor;
        this.studyGroup = studyGroup;
        this.schedule = schedule;
        this.number = number;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOffice() { return office; }
    public void setOffice(String office) { this.office = office; }

    public String getAcademicSubject() { return academicSubject; }
    public void setAcademicSubject(String academicSubject) { this.academicSubject = academicSubject; }

    public String getProfessor() { return professor; }
    public void setProfessor(String professor) { this.professor = professor; }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(String group) {
        this.studyGroup = group;
    }
}
