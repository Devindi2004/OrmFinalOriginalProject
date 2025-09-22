package org.example.ormfinalproject.model;

import org.example.ormfinalproject.BO.custom.PaymentBO;

import java.util.ArrayList;

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


}
