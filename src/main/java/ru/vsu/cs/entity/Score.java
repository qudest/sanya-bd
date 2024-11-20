package ru.vsu.cs.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "score_id_seq")
    @SequenceGenerator(name = "score_id_seq", sequenceName = "score_id_seq", allocationSize = 1)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
    Subject subject;

    @Column(nullable = false)
    Integer grade;

}
