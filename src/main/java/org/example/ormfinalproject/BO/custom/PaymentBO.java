package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO dto) throws Exception;
    boolean updatePayment(PaymentDTO dto) throws Exception;
    boolean deletePayment(String id) throws Exception;
    List<PaymentDTO> getAll() throws Exception;

    ArrayList<PaymentDTO> getAllPayments() throws Exception;

    List<String> getAllStudentIds() throws Exception;
    List<String> getAllCourseIds() throws Exception;
}