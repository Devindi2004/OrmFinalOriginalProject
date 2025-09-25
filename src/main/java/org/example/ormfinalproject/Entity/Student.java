package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // ,eken auto generate wenwa athin id ywanna one na
    private long studentId;

    private String name;
    private String email;
    private String phone;
    private String address;

    @Column(name = "register_fee")
    private String registerFee;

    @Column(name = "registration_date")
    private String registrationDate;

    public Student(long studentId, String name, String email, String phone, String address, String registerFee, String registrationDate) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registerFee = registerFee;
        this.registrationDate = registrationDate;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();
    public Student(long studentId) {
        this.studentId = studentId;
    }
}
