package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
}
