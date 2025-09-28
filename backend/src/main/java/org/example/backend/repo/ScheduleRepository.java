package org.example.backend.repo;

import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    Optional<List<Schedule>> findByStudyGroup(StudyGroup studyGroup);
    Optional<Schedule> findByStudyGroupAndDate(StudyGroup studyGroup, LocalDate date);
}
