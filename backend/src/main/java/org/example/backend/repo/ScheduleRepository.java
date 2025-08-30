package org.example.backend.repo;

import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findByDate(String date);
    List<Schedule> findByStudyGroup(StudyGroup studyGroup);
}
