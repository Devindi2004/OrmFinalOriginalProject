package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.LessonDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LessonBO extends SuperBO {
    ArrayList<LessonDTO> getAllLesson() throws SQLException, ClassNotFoundException;


    boolean save(LessonDTO lessonDTO) throws SQLException, ClassNotFoundException;

    boolean update(LessonDTO lessonDTO) throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    ArrayList<LessonDTO> getAllLessons();

    boolean deleteLesson(String text) throws Exception;

    boolean saveLesson(LessonDTO dto) throws Exception;

    List<LessonDTO> findAll();

    boolean updateLesson(LessonDTO dto) throws Exception;

    List<String> getAllInstructorIds() throws SQLException, ClassNotFoundException;

    List<String> getAllCourseIds() throws Exception;

    List<String> getAllStudentIds() throws SQLException, ClassNotFoundException;
}
