package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    boolean update(CourseDTO courseDTO) throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    boolean save(CourseDTO courseDTO) throws SQLException, ClassNotFoundException;

    ArrayList<CourseDTO> getAllCourse() throws SQLException, ClassNotFoundException;
}
