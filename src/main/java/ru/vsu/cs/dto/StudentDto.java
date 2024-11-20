package ru.vsu.cs.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class StudentDto {

    private final Long id;

    private final String name;

    private final Long recordBookNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime receiptDate;

    private final SpecialityDto speciality;

    private final Integer course;

    private final Integer groupNumber;

}
