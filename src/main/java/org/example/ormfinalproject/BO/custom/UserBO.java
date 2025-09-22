package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;

    boolean delete(Long id) throws SQLException, ClassNotFoundException;

    boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException;

}
