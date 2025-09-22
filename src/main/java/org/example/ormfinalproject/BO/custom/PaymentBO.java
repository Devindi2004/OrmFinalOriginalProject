package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getAllPayment();
}
