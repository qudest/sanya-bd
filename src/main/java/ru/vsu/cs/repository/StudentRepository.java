package ru.vsu.cs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vsu.cs.entity.Speciality;
import ru.vsu.cs.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByRecordBookNumber(Long recordBookNumber);

    @Query("SELECT s FROM Student s WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'name' AND s.name LIKE %:searchValue%) OR " +
            "(:searchColumn = 'recordBookNumber' AND CAST(s.recordBookNumber AS string) LIKE %:searchValue%) OR " +
            "(:searchColumn = 'receiptDate' AND CAST(s.receiptDate AS string) LIKE %:searchValue%) OR " +
            "(:searchColumn = 'speciality' AND s.speciality.name LIKE %:searchValue%)) OR " +
            "(:searchColumn = 'faculty' AND s.speciality.faculty.name LIKE %:searchValue%) OR " +
            "(:searchColumn = 'course' AND CAST(s.course AS string) LIKE %:searchValue%) OR " +
            "(:searchColumn = 'groupNumber' AND CAST(s.groupNumber AS string) LIKE %:searchValue%)")

    Page<Student> findAllWithFilters(@Param("searchValue") String searchValue,
                                        @Param("searchColumn") String searchColumn,
                                        Pageable pageable);

}
