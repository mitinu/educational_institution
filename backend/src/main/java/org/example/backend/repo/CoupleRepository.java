package org.example.backend.repo;

import org.example.backend.models.Couple;
import org.springframework.data.repository.CrudRepository;

public interface CoupleRepository extends CrudRepository<Couple, Long> {
}
