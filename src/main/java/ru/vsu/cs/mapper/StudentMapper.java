package ru.vsu.cs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.dto.StudentCreationDto;
import ru.vsu.cs.dto.StudentDto;
import ru.vsu.cs.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toDto(Student student);

    List<StudentDto> toDto(List<Student> students);

    Student toEntity(StudentCreationDto studentDto);

    @Mapping(source = "speciality.id", target = "specialityId")
    @Mapping(source = "speciality.faculty.id", target = "facultyId")
    StudentCreationDto toCreationDto(StudentDto student);

}
