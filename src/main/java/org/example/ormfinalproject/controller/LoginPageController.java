package org.example.ormfinalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.ormfinalproject.Entity.User;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.UserDAO;

public class LoginPageController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;


    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @FXML
    void handleForgotPassword(MouseEvent event) {

    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            User user = userDAO.search(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    new Alert(Alert.AlertType.INFORMATION, "Login Successfully").show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoard.fxml"));
                    Parent parent = loader.load();
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                    stage.show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleRegister(ActionEvent event) {

    }

}
