package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.BO.custom.UserBO;
import org.example.ormfinalproject.model.StudentDTO;
import org.example.ormfinalproject.model.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class UserPageController {

    public ImageView backButtonId;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private ImageView backButton;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<UserDTO> tblUsers;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> userDTOS = userBO.getAllUser();

        ObservableList<UserDTO> data = FXCollections.observableArrayList();

        for (UserDTO userDTO : userDTOS) {
            data.add(userDTO);
        }

        tblUsers.setItems(data);
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

    @FXML
    void backButton(MouseEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtRole.clear();
    }

    @FXML
    void handleDeleteUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        Long id = Long.valueOf(txtUserId.getText());

        if (id == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a class to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the class with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = userBO.delete(id);
            if (isDelete) {
                loadtable();
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled").show();
        }
    }

    @FXML
    void handleSaveUser(ActionEvent event) throws SQLException, ClassNotFoundException {

        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();

        UserDTO userDTO = new UserDTO(
               name,
               password,
               role
        );

        System.out.println("dddddddddddd////////////////");

        boolean isSave = userBO.save(userDTO);

        System.out.println("dddddddddddd////////////////...........22");



        if (isSave) {
            System.out.println("dddddddddddd////////////////...........333333333333333");
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void handleUpdateUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();

        UserDTO userDTO = new UserDTO(
                Long.parseLong(txtUserId.getText()),
                name,
                password,
                role
        );

        boolean isUpdate = userBO.update(userDTO);
        if (isUpdate) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        UserDTO selectedItem = tblUsers.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtUserId.setText(String.valueOf(selectedItem.getUserId()));
            txtUserName.setText(selectedItem.getName());
            txtPassword.setText(selectedItem.getPassword());
            txtRole.setText(selectedItem.getRole());
        }
    }


    public void handleBackButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
    }

}



