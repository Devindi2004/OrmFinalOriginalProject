package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.PaymentBO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public ArrayList<PaymentDTO> getAllPayment() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean save(PaymentDTO paymentDTO) {
        return false;
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) {
        return false;
    }
}
