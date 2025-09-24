package org.example.ormfinalproject.model;

import  lombok.*;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@Data

public class PaymentDTO  {
private long paymentId;

private long studentId;
private long courseId;
private String paymentDate;
private String amount;
private String paymentMethod;


    public PaymentDTO(int paymentId, String paymentDate, int studentId, String paymentMethod, String amount) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}
