package org.example.ormfinalproject.model;

public class PaymentDTO {
private int paymentId;

    public PaymentDTO(int paymentId, int studentId, int courseId, String paymentDate, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    private int studentId;
private int courseId;
private String paymentDate;
private double amount;
private String paymentMethod;

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
