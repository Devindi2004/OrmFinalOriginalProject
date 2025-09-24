package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Lesson;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.LessonDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public ArrayList<Lesson> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            Query<Lesson> query = session.createQuery("FROM Lesson", Lesson.class);
            lessons = (ArrayList<Lesson>) query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return (lessons != null) ? lessons : new ArrayList<>();
    }

    @Override
    public boolean save(Lesson lesson) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(lesson);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Lesson lesson) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.update(lesson);
        tx.commit();
        session.close();
        return true;
    }

//    @Override
//    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
//        Session session = FactoryConfigaration.getInstance().getSession();
//        Transaction tx = session.beginTransaction();
//        Lesson lesson = session.get(Lesson.class, id);
//        if (lesson != null) {
//            session.remove(lesson);
//            tx.commit();
//            session.close();
//            return true;
//        } else {
//            tx.rollback();
//            session.close();
//            return false;
//        }
//    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT l.lessonId FROM Lesson l ORDER BY l.lessonId DESC",
                    String.class
            );
            query.setMaxResults(1);
            String lastId = query.uniqueResult();
            if (lastId == null) {
                return "L001";
            }
            int idNum = Integer.parseInt(lastId.replace("L", ""));
            idNum++;
            return String.format("L%03d", idNum);
        } finally {
            session.close();
        }
    }

    @Override
    public Lesson search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            return session.get(Lesson.class, Long.parseLong(id));
        } finally {
            session.close();
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return search(id) != null;
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Lesson lesson = session.get(Lesson.class, id);
        if (lesson != null) {
            session.remove(lesson);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false;
        }
    }
}