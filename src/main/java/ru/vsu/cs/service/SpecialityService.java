package ru.vsu.cs.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dto.SpecialityCreationDto;
import ru.vsu.cs.dto.SpecialityDto;
import ru.vsu.cs.entity.Faculty;
import ru.vsu.cs.entity.Speciality;
import ru.vsu.cs.mapper.SpecialityMapper;
import ru.vsu.cs.repository.SpecialityRepository;

import java.util.List;

@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;
    private final SpecialityMapper mapper = SpecialityMapper.INSTANCE;
    private final FacultyService facultyService;

    public SpecialityService(SpecialityRepository specialityRepository, FacultyService facultyService) {
        this.specialityRepository = specialityRepository;
        this.facultyService = facultyService;
    }

    public Page<SpecialityDto> findAll(int page, int size, String searchValue, String searchColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return specialityRepository.findAllWithFilters(searchValue, searchColumn, pageable).map(mapper::toDto);
    }

    public List<SpecialityDto> findAllByFacultyId(Long facultyId) {
        return mapper.toDto(specialityRepository.findAllByFacultyId(facultyId));
    }

    public SpecialityDto findById(Long id) {
        return mapper.toDto(findEntityById(id));
    }

    public Speciality findEntityById(Long id) {
        return specialityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public SpecialityCreationDto toCreationDto(SpecialityDto speciality) {
        return mapper.toCreationDto(speciality);
    }

    public void save(SpecialityCreationDto speciality) {
        Speciality entity = mapper.toEntity(speciality);
        entity.setFaculty(facultyService.findEntityById(speciality.getFacultyId()));
        specialityRepository.save(entity);
    }

    public void update(Long id, SpecialityCreationDto speciality) {
        Faculty faculty = facultyService.findEntityById(speciality.getFacultyId());
        Speciality entity = mapper.toEntity(speciality);
        entity.setFaculty(faculty);
        entity.setId(id);
        specialityRepository.save(entity);
    }

    public void delete(Long id) {
        specialityRepository.deleteById(id);
    }
}
