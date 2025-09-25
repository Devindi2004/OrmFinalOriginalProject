package org.example.ormfinalproject.dao.custom;

import org.example.ormfinalproject.dao.CrudDAO;
import org.example.ormfinalproject.Entity.Student;

public interface StudentDAO extends CrudDAO<Student, String> {
    boolean delete(Long id);

    Student findById(long id) throws Exception;
}
