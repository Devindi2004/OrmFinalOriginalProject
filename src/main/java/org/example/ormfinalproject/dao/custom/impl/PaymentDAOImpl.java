package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Payment;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.PaymentDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfigaration factoryConfiguration = FactoryConfigaration.getInstance();
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        List<Payment> list = session.createQuery("FROM Payment", Payment.class).list();
        session.close();
        return (ArrayList<Payment>) list;

    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        System.out.println("saving with"+entity);
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException, Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            Payment payment = session.get(Payment.class, Long.parseLong(String.valueOf(id)));
            if (payment != null) {
                payment.setStudent(null);
                payment.setCourse(null); // Prevent FK constraint errors when deleting
                session.remove(payment); // Remove payment
            }
            tx.commit();
            return payment != null;
        }
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