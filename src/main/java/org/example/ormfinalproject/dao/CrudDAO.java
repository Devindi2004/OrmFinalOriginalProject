package org.example.ormfinalproject.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(T customerDTO) throws SQLException, ClassNotFoundException;
    public boolean update(T customerDTO) throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(long id) throws SQLException, ClassNotFoundException;
    public String generateNewId() throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;


}