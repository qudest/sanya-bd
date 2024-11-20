package ru.vsu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
