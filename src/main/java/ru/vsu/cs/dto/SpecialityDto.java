package ru.vsu.cs.dto;

import lombok.Data;

@Data
public class SpecialityDto {

    private Long id;

    private String name;

    private String description;

    private FacultyDto faculty;

}
