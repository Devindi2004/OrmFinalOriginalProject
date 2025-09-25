package org.example.ormfinalproject.dao.custom;

import org.example.ormfinalproject.dao.CrudDAO;
import org.example.ormfinalproject.Entity.Instructor;

public interface InstructorDAO extends CrudDAO<Instructor, String> {
    boolean delete(Long id);
    Instructor findById(long id) throws Exception;
}
