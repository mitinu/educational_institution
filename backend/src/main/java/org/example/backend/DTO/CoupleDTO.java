package org.example.backend.DTO;

import org.example.backend.models.Couple;
import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;

import java.time.LocalDate;

public class CoupleDTO {
    private Long idCouple;
    private int number;
    private String office;
    private String academicSubject;
    private String professor;
    private String studyGroup;
    private Long idSchedule;
    private LocalDate date;
    private Long idStudyGroup;
    private String titleStudyGroup;
    private int course;

    public CoupleDTO() {}

    public CoupleDTO(Couple couple) {
        this.idCouple = couple.getId();
        this.number = couple.getNumber();
        this.office = couple.getOffice();
        this.academicSubject = couple.getAcademicSubject();
        this.professor = couple.getProfessor();
        this.studyGroup = couple.getStudyGroup();

        Schedule schedule = couple.getSchedule();
        this.idSchedule = schedule.getId();
        this.date = schedule.getDate();

        StudyGroup studyGroup = schedule.getStudyGroup();
        this.idStudyGroup = studyGroup.getId();
        this.titleStudyGroup = studyGroup.getTitle();
        this.course = studyGroup.getCourse();
    }


    public Long getIdCouple() {
        return idCouple;
    }

    public void setIdCouple(Long idCouple) {
        this.idCouple = idCouple;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getAcademicSubject() {
        return academicSubject;
    }

    public void setAcademicSubject(String academicSubject) {
        this.academicSubject = academicSubject;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(String studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getIdStudyGroup() {
        return idStudyGroup;
    }

    public void setIdStudyGroup(Long idStudyGroup) {
        this.idStudyGroup = idStudyGroup;
    }

    public String getTitleStudyGroup() {
        return titleStudyGroup;
    }

    public void setTitleStudyGroup(String titleStudyGroup) {
        this.titleStudyGroup = titleStudyGroup;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
