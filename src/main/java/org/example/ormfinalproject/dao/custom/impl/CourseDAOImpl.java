package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.CourseDAO;
import org.example.ormfinalproject.Entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {
    private Session session;

    @Override
    public ArrayList<Course> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Course> query = session.createQuery("FROM Course ", Course.class);
            courses = (ArrayList<Course>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (courses != null) ? courses : new ArrayList<>(); // ensure non-null
    }


    @Override
    public boolean save(Course courseDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.persist(courseDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course courseDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.merge(courseDTO);
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
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        // Load the entity first
        Course course = session.get(Course.class, id);
        if (course != null) {
            session.remove(course);
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
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Instructor c ORDER BY c.id DESC",
                    String.class
            );
            query.setMaxResults(1);

            String lastId = query.uniqueResult();

            if (lastId == null) {
                return "S001";
            }

            int idNum = Integer.parseInt(lastId.replace("S", ""));
            idNum++;
            return String.format("S%03d", idNum);

        } finally {
            session.close();
        }
    }

    @Override
    public Course search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
