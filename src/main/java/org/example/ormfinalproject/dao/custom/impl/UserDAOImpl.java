package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.Entity.User;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
        public ArrayList<User> getAll() {
            Session session = FactoryConfigaration.getInstance().getSession();
            Transaction transaction = null;
            ArrayList<User> users = new ArrayList<>();

            try {
                transaction = session.beginTransaction();
                users = (ArrayList<User>) session.createQuery("FROM User", User.class).list();
                transaction.commit();   // ✅ commit, not rollback
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close(); // ✅ always close
            }

            return users;
        }

        @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User userDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.merge(userDTO);
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

        User user = session.get(User.class, id);
        if (user != null) {
            session.remove(user);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            // Correct alias usage + ORDER BY
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM User c ORDER BY c.id DESC",
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
    public User search(String name) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.createQuery("From User where name = :name", User.class).setParameter("name", name).uniqueResult();
        transaction.commit();
        session.close();
        return user;

    }
}
