package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.dao.custom.StudentDAO;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;


public class StudentDAOImpl implements StudentDAO {
    private Session session;

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Student> query = session.createQuery("FROM Student", Student.class);
            students = (ArrayList<Student>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (students != null) ? students : new ArrayList<>(); // ensure non-null
    }

    @Override
    public boolean save(Student studentDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(studentDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student studentDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(studentDTO);
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
    public boolean delete(Long id) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        // Load the entity first
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false; // student not found
        }
    }


    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            // Correct alias usage + ORDER BY
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Student c ORDER BY c.id DESC",
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
    public Student search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
