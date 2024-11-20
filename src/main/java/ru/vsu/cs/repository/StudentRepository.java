package ru.vsu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByRecordBookNumber(Long recordBookNumber);

}
