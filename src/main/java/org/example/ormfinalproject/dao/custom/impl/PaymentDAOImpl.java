package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.dao.custom.PaymentDAO;
import org.example.ormfinalproject.Entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
