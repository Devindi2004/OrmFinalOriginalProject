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
    private long lessonID;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String status;

    public Lesson(Date date, String time, String status, Student student, Course course, Instructor instructor) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.student = student;
        this.course = course;
        this.instructor = instructor;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private  Course course;
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
}