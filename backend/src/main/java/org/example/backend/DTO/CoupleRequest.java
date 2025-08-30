package org.example.backend.DTO;

public class CoupleRequest {
    private String office;
    private String academicSubject;
    private String professor;
    public CoupleRequest() {}

    public CoupleRequest(String office,String academicSubject,String professor){
        this.office = office;
        this.academicSubject = academicSubject;
        this.professor = professor;
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
}
