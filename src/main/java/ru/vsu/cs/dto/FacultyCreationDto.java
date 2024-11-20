package ru.vsu.cs.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FacultyCreationDto {

    @NotEmpty(message = "Name is required")
    @Size(max = 128, message = "Name is too long, max length is 128")
    private String name;

    @NotEmpty(message = "Description is required")
    @Size(max = 255, message = "Description is too long, max length is 255")
    private String description;

}
