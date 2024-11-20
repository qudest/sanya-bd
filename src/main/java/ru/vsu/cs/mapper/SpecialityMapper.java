package ru.vsu.cs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.dto.SpecialityCreationDto;
import ru.vsu.cs.dto.SpecialityDto;
import ru.vsu.cs.entity.Speciality;

import java.util.List;

@Mapper
public interface SpecialityMapper {

    SpecialityMapper INSTANCE = Mappers.getMapper(SpecialityMapper.class);

    SpecialityDto toDto(Speciality speciality);

    List<SpecialityDto> toDto(List<Speciality> specialities);

    Speciality toEntity(SpecialityCreationDto specialityDto);

    SpecialityCreationDto toCreationDto(SpecialityDto speciality);

}
