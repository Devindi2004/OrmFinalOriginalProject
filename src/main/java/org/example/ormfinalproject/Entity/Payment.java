package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private long paymentId;

    @Column(name = "payment_date", nullable = false, length = 20)
    private String paymentDate;

    @Column(name = "amount", nullable = false)
    private String amount;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    public Payment(int paymentId, int studentId, int courseId, String paymentDate, String amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }


    public Payment(String paymentDate, String paymentMethod, String amount) {
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    // 1:1 with Course (owning side)
    @OneToOne
    @JoinColumn(name = "course_id", unique = true, nullable = false)
    private Course course;

    public Payment(String paymentDate, String paymentMethod, String amount, Student student, Course course) {
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.student = student;
        this.course = course;
    }

    public Payment(long paymentId, String paymentDate, String paymentMethod, String amount, Student student, Course course) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.student = student;
        this.course = course;
    }
}


