package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private long paymentId;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "course_id", nullable = false)
    private long courseId;

    @Column(name = "payment_date", nullable = false, length = 20)
    private String paymentDate;

    @Column(name = "amount", nullable = false)
    private String amount;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    public Payment(int paymentId, int studentId, int courseId, String paymentDate, String amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}


