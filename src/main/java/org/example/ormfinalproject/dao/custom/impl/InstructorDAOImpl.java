package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.InstructorDAO;
import org.example.ormfinalproject.Entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorDAOImpl implements InstructorDAO {
    private Session session;

    @Override
    public ArrayList<Instructor> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Instructor> instructors = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Instructor> query = session.createQuery("FROM Instructor ", Instructor.class);
            instructors = (ArrayList<Instructor>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (instructors != null) ? instructors : new ArrayList<>(); // ensure non-null
    }

    @Override
    public boolean save(Instructor instructorDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(instructorDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Instructor instructorDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(instructorDTO);
        session.getTransaction().commit();
        session.close();
        return true;
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
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            // Correct alias usage + ORDER BY
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Instructor c ORDER BY c.id DESC",
                    String.class
            );
            query.setMaxResults(1);

            String lastId = query.uniqueResult();

            // If no records yet, return first ID
            if (lastId == null) {
                return "S001";
            }

            // Otherwise, increment the numeric part of the ID
            int idNum = Integer.parseInt(lastId.replace("S", ""));
            idNum++;
            return String.format("S%03d", idNum);

        } finally {
            session.close();
        }
    }

    @Override
    public Instructor search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        // Load the entity first
        Instructor instructor = session.get(Instructor.class, id);
        if (instructor != null) {
            session.remove(instructor);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false; // student not found
        }
    }
}
