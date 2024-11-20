package ru.vsu.cs.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class StudentCreationDto {

    @NotEmpty(message = "Name is required")
    @Size(max = 128, message = "Name is too long, max length is 128")
    private String name;

    @NotNull(message = "Record book number is required")
    @Min(value = 1, message = "Record book number must be greater than 0")
    @Max(value = Long.MAX_VALUE, message = "Record book number is too long")
    private Long recordBookNumber;

    @NotNull(message = "Receipt date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime receiptDate;

    @NotNull(message = "Speciality is required")
    private Long specialityId;

    @NotNull(message = "Faculty is required")
    private Long facultyId;

    @NotNull(message = "Course is required")
    @Min(value = 1, message = "Course must be from 1 to 5")
    @Max(value = 5, message = "Course must be from 1 to 5")
    private Integer course;

    @NotNull(message = "Group number is required")
    @Min(value = 1, message = "Group number must be greater than 0")
    private Integer groupNumber;

}
