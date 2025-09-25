package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String duration;

    @Column(nullable = false, length = 100)
    private String fee;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

    public Course(long courseId, String name, String duration, String fee) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }

    public Course(long courseId) {
        this.courseId = courseId;
    }
    // Bidirectional 1:1 (inverse side)
    @OneToOne(mappedBy = "course", orphanRemoval = true)
    private Payment payment;
}
