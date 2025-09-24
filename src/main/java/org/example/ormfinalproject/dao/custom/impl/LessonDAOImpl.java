package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Lesson;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.LessonDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAOImpl implements LessonDAO {
    private final FactoryConfigaration factoryConfiguration = FactoryConfigaration.getInstance();


    @Override
    public ArrayList<Lesson> getAll() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        List<Lesson> list = session.createQuery("FROM Lesson", Lesson.class).list();
        session.close();
        return (ArrayList<Lesson>) list;
    }

    @Override
    public boolean save(Lesson lesson) throws SQLException {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(lesson);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Lesson> getall() {
        Session session = factoryConfiguration.getSession();
        List<Lesson> list = session.createQuery("FROM Lesson", Lesson.class).list();
        session.close();
        return (ArrayList<Lesson>) list;
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        Lesson lesson = session.get(Lesson.class, Long.parseLong(id));
        if (lesson != null) {
            session.remove(lesson);
        }
        tx.commit();
        session.close();
        return lesson != null;
    }

    @Override
    public boolean update(Lesson lesson) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(lesson);
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
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Lesson search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}