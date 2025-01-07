package ru.vsu.cs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vsu.cs.entity.Faculty;
import ru.vsu.cs.entity.Speciality;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

    List<Speciality> findAllByFacultyId(Long facultyId);

    @Query("SELECT s FROM Speciality s WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'name' AND s.name LIKE %:searchValue%) OR " +
            "(:searchColumn = 'description' AND s.description LIKE %:searchValue%) OR " +
            "(:searchColumn = 'faculty' AND s.faculty.name LIKE %:searchValue%))")
    Page<Speciality> findAllWithFilters(@Param("searchValue") String searchValue,
                                     @Param("searchColumn") String searchColumn,
                                     Pageable pageable);

}
