package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "student_id", nullable = false)
    private int studentId;

    @Column(name = "course_id", nullable = false)
    private int courseId;

    @Column(name = "payment_date", nullable = false, length = 20)
    private String paymentDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    // No-arg constructor required by JPA
    public Payment() {}

    public Payment(int paymentId, int studentId, int courseId, String paymentDate, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
