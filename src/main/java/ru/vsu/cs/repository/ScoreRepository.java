package ru.vsu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
