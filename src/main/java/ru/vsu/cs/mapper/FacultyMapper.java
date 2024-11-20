package ru.vsu.cs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.dto.FacultyCreationDto;
import ru.vsu.cs.dto.FacultyDto;
import ru.vsu.cs.entity.Faculty;

import java.util.List;

@Mapper(uses = {SpecialityMapper.class})
public interface FacultyMapper {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    FacultyDto toDto(Faculty faculty);

    List<FacultyDto> toDto(List<Faculty> faculties);

    Faculty toEntity(FacultyCreationDto facultyDto);

    FacultyCreationDto toCreationDto(FacultyDto faculty);
}
