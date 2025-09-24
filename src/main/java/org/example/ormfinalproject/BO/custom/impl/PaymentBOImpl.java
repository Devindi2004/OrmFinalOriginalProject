package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.PaymentBO;
import org.example.ormfinalproject.Entity.Instructor;
import org.example.ormfinalproject.Entity.Payment;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.InstructorDAO;
import org.example.ormfinalproject.dao.custom.PaymentDAO;
import org.example.ormfinalproject.model.InstructorDTO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = paymentDAO.getAll();

        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment p : payments) {
            paymentDTOS.add(new PaymentDTO(p.getPaymentId(),p.getStudentId(),p.getCourseId(),p.getPaymentDate(),p.getPaymentMethod(),p.getAmount()));
        }
        return paymentDTOS;
    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(Long.valueOf(String.valueOf(id)));
    }

    @Override
    public boolean save(PaymentDTO p) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(p.getPaymentId(),p.getStudentId(),p.getCourseId(),p.getPaymentDate(),p.getPaymentMethod(),p.getAmount()));
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(paymentDTO.getPaymentId(),paymentDTO.getStudentId(),paymentDTO.getCourseId(),paymentDTO.getPaymentDate(),paymentDTO.getPaymentMethod(),paymentDTO.getAmount()));
    }
}
