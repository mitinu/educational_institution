package org.example.backend.repo;

import org.example.backend.models.Couple;
import org.example.backend.models.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CoupleRepository extends CrudRepository<Couple, Long> {
    List<Couple> findBySchedule_Date(LocalDate date);
    Optional<List<Couple>> findBySchedule(Schedule schedule);
}
