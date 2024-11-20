package ru.vsu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.entity.Speciality;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

    List<Speciality> findAllByFacultyId(Long facultyId);

}
