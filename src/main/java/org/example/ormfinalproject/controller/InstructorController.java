package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.BO.custom.InstructorBO;
import org.example.ormfinalproject.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Long.parseLong;

public class InstructorController {

    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSTRUCTOR);


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView <InstructorDTO> tblInstructor;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<InstructorDTO> instructorDTOS = instructorBO.getAllInstructor();

        ObservableList<InstructorDTO> data = FXCollections.observableArrayList();

        for (InstructorDTO instructorDTO : instructorDTOS) {
            data.add(instructorDTO);
        }

        tblInstructor.setItems(data);
    }

    private void setCellValueFactory() {
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    @FXML
    void handleAddPayment(ActionEvent event) throws SQLException, ClassNotFoundException {
//        long instructorId = parseLong(txtInstructorId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String availability = txtAvailability.getText();

        InstructorDTO instructorDTO = new InstructorDTO(
//                instructorId,
                name,
                email,
                phone
        );

        boolean isSave = instructorBO.save(instructorDTO);

        if (isSave) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void handleClear(ActionEvent event) throws ClassNotFoundException, SQLException {
        txtInstructorId.clear();
        txtPhone.clear();
        txtName.clear();
        txtEmail.clear();
        txtAvailability.clear();
    }

    @FXML
    void handleDeletePayment(ActionEvent event)throws SQLException,ClassNotFoundException {
        Long id = Long.valueOf(txtInstructorId.getText());

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
            boolean isDelete = instructorBO.delete(id);
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
    void handleUpdatePayment(ActionEvent event) throws ClassNotFoundException, SQLException {
        long instructorId = parseLong(txtInstructorId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String availability = txtAvailability.getText();

        InstructorDTO instructorDTO = new InstructorDTO(
                instructorId,
                name,
                email,
                phone,
                availability
        );

        boolean isUpdate = instructorBO.update(instructorDTO);

        if (isUpdate) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        InstructorDTO selectedItem = tblInstructor.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtInstructorId.setText(String.valueOf(selectedItem.getInstructorId()));
            txtName.setText(selectedItem.getName());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());
            txtAvailability.setText(selectedItem.getAvailability());
        }
    }
}
