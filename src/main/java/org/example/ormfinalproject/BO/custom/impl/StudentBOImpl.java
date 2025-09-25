package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.StudentBO;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.StudentDAO;
import org.example.ormfinalproject.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = studentDAO.getAll();

        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : students) {
            studentDTOS.add(new StudentDTO(s.getStudentId(),s.getName(),s.getEmail(),s.getPhone(),s.getAddress(),s.getRegisterFee(),s.getRegistrationDate()));
        }
        return studentDTOS;
    }
    @Override
    public StudentDTO findById(String id) throws Exception {
        Student student = studentDAO.findById(Long.parseLong(id));
        if (student == null) return null;
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getRegisterFee(),
                student.getRegistrationDate());
    }
//    @Override
//    public String getNextId() throws SQLException, ClassNotFoundException {
//        return studentDAO.generateNewId();
//    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean save(StudentDTO s) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(s.getStudentId(),s.getName(),s.getEmail(),s.getPhone(),s.getAddress(),s.getRegisterFee(),s.getRegistrationDate()));
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getEmail(),studentDTO.getPhone(),studentDTO.getAddress(),studentDTO.getRegisterFee(),studentDTO.getRegistrationDate()));
    }
}