package ru.vsu.cs.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "speciality")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speciality_id_seq")
    @SequenceGenerator(name = "speciality_id_seq", sequenceName = "speciality_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "faculty_id")
    Faculty faculty;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.REMOVE, orphanRemoval = true)
    Set<Student> students;

}
