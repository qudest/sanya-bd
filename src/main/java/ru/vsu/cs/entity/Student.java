package ru.vsu.cs.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    Long recordBookNumber;

    @Column(nullable = false)
    LocalDateTime receiptDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "speciality_id")
    Speciality speciality;

    @Column(nullable = false)
    Integer course;

    @Column(nullable = false)
    Integer groupNumber;

}
