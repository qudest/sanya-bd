package ru.vsu.cs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vsu.cs.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("SELECT f FROM Faculty f WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'name' AND f.name LIKE %:searchValue%) OR " +
            "(:searchColumn = 'description' AND f.description LIKE %:searchValue%))")
    Page<Faculty> findAllWithFilters(@Param("searchValue") String searchValue,
                                     @Param("searchColumn") String searchColumn,
                                     Pageable pageable);

}
