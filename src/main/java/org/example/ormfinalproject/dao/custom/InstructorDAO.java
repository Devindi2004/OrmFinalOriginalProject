package org.example.ormfinalproject.dao.custom;

import org.example.ormfinalproject.dao.CrudDAO;
import org.example.ormfinalproject.Entity.Instructor;

public interface InstructorDAO extends CrudDAO<Instructor> {
    boolean delete(Long id);
}
