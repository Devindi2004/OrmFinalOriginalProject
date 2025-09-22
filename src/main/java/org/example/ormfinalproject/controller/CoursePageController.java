package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.BO.custom.CourseBO;
import org.example.ormfinalproject.model.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CoursePageController {


    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    public Button btnSave;
    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFees;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<CourseDTO> tblCourses;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFees;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadTable();
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ArrayList<CourseDTO> courseDTOS = courseBO.getAllCourse();

        ObservableList<CourseDTO> data = FXCollections.observableArrayList();

        for (CourseDTO courseDTO : courseDTOS) {
            data.add(courseDTO);
        }

        System.out.println("courseDTOS" + courseDTOS.toString());
        tblCourses.setItems(data);
        System.out.println("table loaded" + data);
    }

    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFees.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    @FXML
    void handleAddCourse(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtCourseId.getText().isEmpty() || txtCourseName.getText().isEmpty()
                || txtDuration.getText().isEmpty() || txtFees.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        long courseId = Long.parseLong(txtCourseId.getText().trim());
        String name = txtCourseName.getText().trim();
        String duration = txtDuration.getText().trim();
        String fee = txtFees.getText().trim();

        CourseDTO courseDTO = new CourseDTO(
                name,
                duration,
                fee
        );

        boolean isSave = courseBO.save(courseDTO);

        if (isSave) {
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtFees.clear();
    }

    @FXML
    void handleDeleteCourse(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtCourseId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a class to delete.", ButtonType.OK).show();
            return;
        }

        Long id = Long.valueOf(txtCourseId.getText().trim());

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the class with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = courseBO.delete(id);
            if (isDelete) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled").show();
        }
    }

    @FXML
    void handleUpdateCourse(ActionEvent event) throws SQLException, ClassNotFoundException {
        long courseId = Long.parseLong(txtCourseId.getText());
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String fee = txtFees.getText();

        System.out.println(name + duration + fee);

        CourseDTO courseDTO = new CourseDTO(
                courseId,
                name,
                duration,
                fee
        );

        System.out.println(courseId);
        boolean isUpdate = courseBO.update(courseDTO);
        System.out.println(isUpdate);

        if (isUpdate) {
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        CourseDTO selectedItem = tblCourses.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtCourseId.setText(String.valueOf(selectedItem.getCourseId()));
            txtCourseName.setText(selectedItem.getName());
            txtDuration.setText(selectedItem.getDuration());
            txtFees.setText(selectedItem.getFee());
        }
    }

    public void handleSaveCourse(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String fee = txtFees.getText();

        CourseDTO courseDTO = new CourseDTO(
                name,
                duration,
                fee
        );

        boolean isSave = courseBO.save(courseDTO);

        if (isSave) {
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    public void backButton(MouseEvent mouseEvent) {

    }
}
