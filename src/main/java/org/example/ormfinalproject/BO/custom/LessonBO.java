package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.LessonDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LessonBO extends SuperBO {
    ArrayList<LessonDTO> getAllLesson() throws SQLException, ClassNotFoundException;


    boolean save(LessonDTO lessonDTO) throws SQLException, ClassNotFoundException;

    boolean update(LessonDTO lessonDTO) throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    ArrayList<LessonDTO> getAllLessons();
}
