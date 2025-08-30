package org.example.backend.models;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "couple1_id", referencedColumnName = "id")
    private Couple couple1;
    @ManyToOne
    @JoinColumn(name = "couple2_id", referencedColumnName = "id")
    private Couple couple2;
    @ManyToOne
    @JoinColumn(name = "couple3_id", referencedColumnName = "id")
    private Couple couple3;
    @ManyToOne
    @JoinColumn(name = "couple4_id", referencedColumnName = "id")
    private Couple couple4;
    @ManyToOne
    @JoinColumn(name = "couple5_id", referencedColumnName = "id")
    private Couple couple5;
    @ManyToOne
    @JoinColumn(name = "couple6_id", referencedColumnName = "id")
    private Couple couple6;


    private String date;

    @ManyToOne
    @JoinColumn(name = "Study_group_id", referencedColumnName = "id")
    private StudyGroup studyGroup;
    public Schedule() {}

    public Schedule(List<Couple> listCouple, String date, StudyGroup studyGroup) {
        if (listCouple.size()!=6){
            throw new IllegalArgumentException("The list must contain exactly 6 couples");
        }
        this.couple1 = listCouple.get(0);
        this.couple2 = listCouple.get(1);
        this.couple3 = listCouple.get(2);
        this.couple4 = listCouple.get(3);
        this.couple5 = listCouple.get(4);
        this.couple6 = listCouple.get(5);
        this.date = date;
        this.studyGroup = studyGroup;

    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Couple getCouple1() {
        return couple1;
    }
    public void setCouple1(Couple couple1) {
        this.couple1 = couple1;
    }

    public Couple getCouple2() {
        return couple2;
    }
    public void setCouple2(Couple couple2) {
        this.couple2 = couple2;
    }

    public Couple getCouple3() {
        return couple3;
    }
    public void setCouple3(Couple couple3) {
        this.couple3 = couple3;
    }

    public Couple getCouple4() {
        return couple4;
    }
    public void setCouple4(Couple couple4) {
        this.couple4 = couple4;
    }

    public Couple getCouple5() {
        return couple5;
    }
    public void setCouple5(Couple couple5) {
        this.couple5 = couple5;
    }

    public Couple getCouple6() {
        return couple6;
    }
    public void setCouple6(Couple couple6) {
        this.couple6 = couple6;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }
    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public List<Couple> getListCouple() {
        return Arrays.asList(couple1, couple2, couple3, couple4, couple5, couple6);
    }
    public void setListCouple(List<Couple> listCouple) {
        if (listCouple.size() != 6) {
            throw new IllegalArgumentException("The list must contain exactly 6 couples");
        }
        this.couple1 = listCouple.get(0);
        this.couple2 = listCouple.get(1);
        this.couple3 = listCouple.get(2);
        this.couple4 = listCouple.get(3);
        this.couple5 = listCouple.get(4);
        this.couple6 = listCouple.get(5);
    }
}
