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
import javafx.stage.Stage;
import org.example.ormfinalproject.BO.custom.StudentBO;
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.model.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class StudentPageController {

    public ImageView backButtonId;
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private TableColumn<?, ?> colRegisterFee;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private DatePicker dpRegistrationDate;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtRegisterFee;

    @FXML
    private TextField txtStudentId;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
       // setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS = studentBO.getAllStudent();

        ObservableList<StudentDTO> data = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : studentDTOS) {
            data.add(studentDTO);
        }

        tblStudent.setItems(data);
    }

//    private void setNextId() throws SQLException, ClassNotFoundException {
//        String nextId = studentBO.getNextId();
//        txtStudentId.setText(nextId);
//    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRegisterFee.setCellValueFactory(new PropertyValueFactory<>("registerFee"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtStudentId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtRegisterFee.clear();
        dpRegistrationDate.setValue(null);
    }

    @FXML
    void handleDeletePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        Long id = Long.valueOf(txtStudentId.getText());

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
            boolean isDelete = studentBO.delete(id);
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
    void handleSavePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        //String studentId = txtStudentId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String registerFee = txtRegisterFee.getText();
        String registrationDate = dpRegistrationDate.getValue().toString();

        StudentDTO studentDTO = new StudentDTO(
               //studentId,
                name,
                email,
                phone,
                address,
                registerFee,
                registrationDate
        );

        boolean isSave = studentBO.save(studentDTO);

        if (isSave) {
            //setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void handleUpdatePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        long studentId = Long.parseLong(txtStudentId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String registerFee = txtRegisterFee.getText();
        String registrationDate = dpRegistrationDate.getValue().toString();

        StudentDTO studentDTO = new StudentDTO(
                studentId,
                name,
                email,
                phone,
                address,
                registerFee,
                registrationDate

        );

        boolean isUpdate = studentBO.update(studentDTO);
        if (isUpdate) {
            //setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }
    public void tableClickOnAction(MouseEvent mouseEvent) {
        StudentDTO selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtStudentId.setText(String.valueOf(selectedItem.getStudentId()));
            txtName.setText(selectedItem.getName());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());
            txtAddress.setText(selectedItem.getAddress());
            txtRegisterFee.setText(selectedItem.getRegisterFee());
            dpRegistrationDate.setValue(LocalDate.parse(selectedItem.getRegistrationDate()));


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
