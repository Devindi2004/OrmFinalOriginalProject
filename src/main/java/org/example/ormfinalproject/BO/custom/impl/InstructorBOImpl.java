package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.InstructorBO;
import org.example.ormfinalproject.Entity.Instructor;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.InstructorDAO;
import org.example.ormfinalproject.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);


    @Override
    public ArrayList<InstructorDTO> getAllInstructor() throws SQLException, ClassNotFoundException {
        ArrayList<Instructor> instructor = instructorDAO.getAll();

        ArrayList<InstructorDTO> instructorDTOS = new ArrayList<>();
        for (Instructor i : instructor) {
            instructorDTOS.add(new InstructorDTO(i.getInstructorId(),i.getName(),i.getEmail(),i.getPhone(),i.getAvailability()));
        }
        return instructorDTOS;
    }

    @Override
    public boolean save(InstructorDTO i) throws SQLException, ClassNotFoundException {
        return instructorDAO.save(new Instructor(i.getInstructorId(),i.getName(),i.getEmail(),i.getPhone(),i.getAvailability()));

    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        return instructorDAO.delete(Long.valueOf(String.valueOf(id)));

    }

    @Override
    public boolean update(InstructorDTO i) throws SQLException, ClassNotFoundException {
        return instructorDAO.update(new Instructor(i.getInstructorId(),i.getName(),i.getEmail(),i.getPhone(), i.getAvailability()));
    }
}
