package ru.vsu.cs.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dto.FacultyCreationDto;
import ru.vsu.cs.dto.FacultyDto;
import ru.vsu.cs.entity.Faculty;
import ru.vsu.cs.mapper.FacultyMapper;
import ru.vsu.cs.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper mapper = FacultyMapper.INSTANCE;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<FacultyDto> findAll() {
        return mapper.toDto(facultyRepository.findAll(Sort.by("id")));
    }

    public FacultyDto findById(Long id) {
        return mapper.toDto(facultyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found with id: " + id)
        ));
    }

    public Faculty findEntityById(Long id) {
        return facultyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found with id: " + id)
        );
    }

    public FacultyCreationDto toCreationDto(FacultyDto faculty) {
        return mapper.toCreationDto(faculty);
    }

    public void save(FacultyCreationDto faculty) {
        facultyRepository.save(mapper.toEntity(faculty));
    }

    public void update(Long id, FacultyCreationDto faculty) {
        Faculty entityById = findEntityById(id);
        Faculty entity = mapper.toEntity(faculty);
        entity.setId(id);
        entity.setSpecialities(entityById.getSpecialities());
        facultyRepository.save(entity);
    }

    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }
}
