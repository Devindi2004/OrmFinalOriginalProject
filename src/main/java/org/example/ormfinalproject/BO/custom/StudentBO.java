package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {

    ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;

//    String getNextId() throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    boolean save(StudentDTO classDTO) throws SQLException, ClassNotFoundException;

    boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
}
