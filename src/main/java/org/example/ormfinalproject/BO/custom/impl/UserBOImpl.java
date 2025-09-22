package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.UserBO;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.Entity.User;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.UserDAO;
import org.example.ormfinalproject.model.StudentDTO;
import org.example.ormfinalproject.model.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        for (User u : users) {
            userDTOS.add(new UserDTO(u.getUserId(), u.getName(), u.getPassword(), u.getRole()));
        }
        return userDTOS;
    }


    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User(userDTO.getUserId(),userDTO.getName(),userDTO.getPassword(),userDTO.getRole());
        System.out.println("Data come from controller.......................");
        return userDAO.save(user);
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(userDTO.getUserId(),userDTO.getName(),userDTO.getPassword(),userDTO.getRole()));
    }

}
