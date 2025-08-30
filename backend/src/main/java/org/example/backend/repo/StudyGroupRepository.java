package org.example.backend.repo;

import org.example.backend.models.StudyGroup;
import org.springframework.data.repository.CrudRepository;

public interface StudyGroupRepository extends CrudRepository<StudyGroup, Long> {
    boolean existsByTitle(String title);
}
