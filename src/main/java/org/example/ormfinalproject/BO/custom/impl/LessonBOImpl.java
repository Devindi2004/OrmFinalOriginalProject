package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.LessonBO;
import org.example.ormfinalproject.Entity.Course;
import org.example.ormfinalproject.Entity.Instructor;
import org.example.ormfinalproject.Entity.Lesson;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.CourseDAO;
import org.example.ormfinalproject.dao.custom.InstructorDAO;
import org.example.ormfinalproject.dao.custom.LessonDAO;
import org.example.ormfinalproject.dao.custom.StudentDAO;
import org.example.ormfinalproject.model.LessonDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LessonBOImpl implements LessonBO {

    private final LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LESSON);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public boolean saveLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(dto.getInstructorId());
        Course  course = courseDAO.findById(dto.getCourseId());
        System.out.println(dto.getStudentId()+"dto student id");
        Student student = studentDAO.findById(dto.getStudentId());
        System.out.println(student+" st");
        System.out.println(dto.getLessonId()+""+dto.getDate()+""+dto.getTime()+""+dto.getStatus()+""+dto.getStudentId()+""+dto.getCourseId()+""+dto.getInstructorId()+"boImpl");
        Lesson lesson = new Lesson(
            dto.getLessonId(),
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                student,
                course,
                instructor
        );
        return lessonDAO.save(lesson);
    }

    @Override
    public boolean updateLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(dto.getInstructorId());
        Course course = courseDAO.findById(dto.getCourseId());
        Student student = studentDAO.findById(dto.getStudentId());

        Lesson lesson = new Lesson(
                dto.getLessonId(),
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                student,
                course,
                instructor
        );
        return lessonDAO.update(lesson);
    }



    @Override
    public boolean deleteLesson(String id) throws Exception {
        return lessonDAO.delete(Long.parseLong(id));
    }

    @Override
    public List<LessonDTO> findAll() throws SQLException, ClassNotFoundException {
        return lessonDAO.getAll().stream().map(lesson ->
                new LessonDTO(
                        lesson.getLessonID(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getStatus(),
                        lesson.getStudent().getStudentId(),
                        lesson.getCourse().getCourseId(),
                        lesson.getInstructor().getInstructorId()
                )).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllInstructorIds() throws SQLException, ClassNotFoundException {
        List<Instructor> list = instructorDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Instructor i : list) {
            idList.add(String.valueOf(i.getInstructorId()));
        }
        return idList;
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Course i : list) {
            idList.add(String.valueOf(i.getCourseId()));
        }
        return idList;
    }

    @Override
    public List<String> getAllStudentIds() throws SQLException, ClassNotFoundException {
        List<Student> list = studentDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Student i : list) {
            idList.add(String.valueOf(i.getStudentId()));
        }
        return idList;
    }
}