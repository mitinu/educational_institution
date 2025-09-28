package org.example.backend.DTO;

import jakarta.persistence.EntityNotFoundException;
import org.example.backend.models.Couple;
import org.example.backend.models.Schedule;
import org.example.backend.repo.CoupleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDTO {
    private Long id;
    private LocalDate date;
    private List<Couple> couples;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Schedule schedule, List<Couple> couples) {
        this.id = schedule.getId();
        this.date = schedule.getDate();
        this.couples = couples;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Couple> getCouples() {
        return couples;
    }

    public void setCouples(List<Couple> couples) {
        this.couples = couples;
    }
}
