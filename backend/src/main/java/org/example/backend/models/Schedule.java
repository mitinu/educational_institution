package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class
Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "Study_group_id", referencedColumnName = "id")
    private StudyGroup studyGroup;



    public Schedule() {}

    public Schedule(String date, StudyGroup studyGroup) {

        this.date = LocalDate.parse(date);
        this.studyGroup = studyGroup;
    }
    public Schedule(LocalDate date, StudyGroup studyGroup) {

        this.date = date;
        this.studyGroup = studyGroup;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }
    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
