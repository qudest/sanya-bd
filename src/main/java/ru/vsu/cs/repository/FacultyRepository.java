package ru.vsu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
