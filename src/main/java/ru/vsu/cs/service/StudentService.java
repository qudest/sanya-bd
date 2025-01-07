package ru.vsu.cs.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dto.StudentCreationDto;
import ru.vsu.cs.dto.StudentDto;
import ru.vsu.cs.entity.Speciality;
import ru.vsu.cs.entity.Student;
import ru.vsu.cs.mapper.StudentMapper;
import ru.vsu.cs.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper = StudentMapper.INSTANCE;
    private final SpecialityService specialityService;

    public StudentService(StudentRepository studentRepository, SpecialityService specialityService) {
        this.studentRepository = studentRepository;
        this.specialityService = specialityService;
    }

    public Page<StudentDto> findAll(int page, int size, String searchValue, String searchColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return studentRepository.findAllWithFilters(searchValue, searchColumn, pageable).map(mapper::toDto);
    }

    public StudentDto findById(Long id) {
        return mapper.toDto(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public boolean existsByRecordBookNumber(Long recordBookNumber) {
        return studentRepository.existsByRecordBookNumber(recordBookNumber);
    }

    public StudentCreationDto toCreationDto(StudentDto student) {
        return mapper.toCreationDto(student);
    }

    public void save(StudentCreationDto student) {
        Speciality speciality = specialityService.findEntityById(student.getSpecialityId());
        Student entity = mapper.toEntity(student);
        entity.setSpeciality(speciality);
        System.out.println(entity);
        studentRepository.save(entity);
    }

    public void update(Long id, StudentCreationDto student) {
        Speciality speciality = specialityService.findEntityById(student.getSpecialityId());
        Student entity = mapper.toEntity(student);
        entity.setSpeciality(speciality);
        entity.setId(id);
        studentRepository.save(entity);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
