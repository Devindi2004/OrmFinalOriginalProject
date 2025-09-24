package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lessonId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}