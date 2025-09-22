package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.dao.custom.LessonDAO;
import org.example.ormfinalproject.Entity.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public ArrayList<Lesson> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Lesson customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Lesson customerDTO) throws SQLException, ClassNotFoundException {
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
    public Lesson search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
