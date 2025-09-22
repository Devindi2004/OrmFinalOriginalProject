package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.CourseBO;
import org.example.ormfinalproject.Entity.Course;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.CourseDAO;
import org.example.ormfinalproject.model.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean update(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        System.out.println("qwwwwww"+courseDTO.getCourseId());
        return courseDAO.update(new Course(courseDTO.getCourseId(),courseDTO.getName(),courseDTO.getDuration(),courseDTO.getFee()));
    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        return courseDAO.delete(id);
    }

    @Override
    public boolean save(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        return courseDAO.save(new Course(courseDTO.getCourseId(),courseDTO.getName(),courseDTO.getDuration(),courseDTO.getFee()));
    }

    @Override
    public ArrayList<CourseDTO> getAllCourse() throws SQLException, ClassNotFoundException {
        ArrayList<Course> course = courseDAO.getAll();

        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course c : course) {
            courseDTOS.add(new CourseDTO(c.getCourseId(),c.getName(),c.getDuration(),c.getFee()));
        }
        return courseDTOS;
    }

}
