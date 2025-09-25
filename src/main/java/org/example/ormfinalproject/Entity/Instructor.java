package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructor")   // maps to instructor table
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment PK
    @Column(name = "instructor_id")
    private long instructorId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(nullable = false, length = 100)
    private String availability;

    public Instructor(long instructorId, String name, String email, String phone, String availability) {
        this.instructorId = instructorId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.availability = availability;
    }

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();


}
