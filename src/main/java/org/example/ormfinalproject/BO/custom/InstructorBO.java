package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InstructorBO extends SuperBO {
    ArrayList<InstructorDTO> getAllInstructor() throws SQLException, ClassNotFoundException;

    boolean save(InstructorDTO instructorDTO) throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    boolean update(InstructorDTO instructorDTO) throws SQLException, ClassNotFoundException;
}
