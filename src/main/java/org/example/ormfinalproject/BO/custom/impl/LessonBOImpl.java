package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.LessonBO;
import org.example.ormfinalproject.Entity.Course;
import org.example.ormfinalproject.Entity.Instructor;
import org.example.ormfinalproject.Entity.Lesson;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.LessonDAO;
import org.example.ormfinalproject.model.LessonDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LessonBOImpl implements LessonBO {


    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LESSON);

    @Override
    public ArrayList<LessonDTO> getAllLesson() throws SQLException, ClassNotFoundException {
        ArrayList<Lesson> lessons = lessonDAO.getAll();
        ArrayList<LessonDTO> lessonDTOS = new ArrayList<>();
        for (Lesson l : lessons) {
            lessonDTOS.add(new LessonDTO(
                    l.getLessonId(),
                    l.getDate(),
                    l.getTime(),
                    l.getStatus(),
                    l.getCourse().getCourseId(),
                    l.getInstructor().getInstructorId(),
                    l.getStudent().getStudentId()
            ));
        }
        return lessonDTOS;
    }


    @Override
    public boolean save(LessonDTO lessonDTO) throws SQLException, ClassNotFoundException {
        Lesson lesson = new Lesson();
        lesson.setDate(lessonDTO.getDate());
        lesson.setTime(lessonDTO.getTime());
        lesson.setStatus(lessonDTO.getStatus());
        lesson.setCourse(new Course(lessonDTO.getCourseId()));
        lesson.setInstructor(new Instructor(lessonDTO.getInstructorId()));
        lesson.setStudent(new Student(lessonDTO.getStudentId()));
        return lessonDAO.save(lesson);
    }

    @Override
    public boolean update(LessonDTO lessonDTO) throws SQLException, ClassNotFoundException {
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonDTO.getLessonId());
        lesson.setDate(lessonDTO.getDate());
        lesson.setTime(lessonDTO.getTime());
        lesson.setStatus(lessonDTO.getStatus());
        lesson.setCourse(new Course(lessonDTO.getCourseId()));
        lesson.setInstructor(new Instructor(lessonDTO.getInstructorId()));
        lesson.setStudent(new Student(lessonDTO.getStudentId()));
        return lessonDAO.update(lesson);
    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        return lessonDAO.delete(id);
    }

    @Override
    public ArrayList<LessonDTO> getAllLessons() {
        return null;
    }
}
