package org.example.ormfinalproject.model;

import  lombok.*;

import java.sql.Date;

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


    public PaymentDTO(Date date, String selectedItem, String text, String paymentMethod, String amount) {
        this.studentId = Long.parseLong(selectedItem);
        this.courseId = Long.parseLong(text);
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = date.toString(); // ← convert Date to String
    }


    public PaymentDTO(long paymentId, String paymentDate, String paymentMethod, String amount, long studentId, long courseId) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}
