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
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.BO.custom.CourseBO;
import org.example.ormfinalproject.BO.custom.PaymentBO;
import org.example.ormfinalproject.model.CourseDTO;
import org.example.ormfinalproject.model.PaymentDTO;
import org.example.ormfinalproject.model.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class PaymentController {

    public ComboBox cbStudentID;
    public ComboBox cbCourseid;
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    @FXML
    private ImageView backButtonId;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cbMethod;

    @FXML
    private TableColumn<?, ?> clmAmount;

    @FXML
    private TableColumn<?, ?> clmCourseId;

    @FXML
    private TableColumn<?, ?> clmPDate;

    @FXML
    private TableColumn<?, ?> clmPaymenMethodtId;

    @FXML
    private TableColumn<?, ?> clmPaymentId;

    @FXML
    private TableColumn<?, ?> clmStudentId;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private DatePicker dpPaymentDate;

    @FXML
    private TableView<PaymentDTO> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtStudentId;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadtable();
        setCellValueFactory();
        setPaymentComboBox();
    }

    private void setPaymentComboBox() {
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Card", "Online");
        cbMethod.setItems(paymentMethods);
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOS = paymentBO.getAllPayment();

        ObservableList<PaymentDTO> data = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            data.add(paymentDTO);
        }

        tblPayment.setItems(data);
    }

    private void setCellValueFactory() {
        clmPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        clmStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        clmCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        clmPDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmPaymenMethodtId.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
    }

    @FXML
    void handleBackButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtPaymentId.clear();
        cbStudentID.setValue(null);
        cbCourseid.setValue(null);
        txtAmount.clear();
        cbMethod.setValue(null);
        dpPaymentDate.setValue(null);
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
            boolean isDelete = paymentBO.delete(id);
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
       String paymentId = txtPaymentId.getText();
       String studentId = cbStudentID.getValue().toString();
       String courseId = cbCourseid.getValue().toString();
       String amount = txtAmount.getText();
       String paymentMethod = cbMethod.getValue().toString();
       String paymentDate = dpPaymentDate.getValue().toString();

       PaymentDTO paymentDTO = new PaymentDTO(
                Long.valueOf(paymentId),
                Long.valueOf(studentId),
                Long.valueOf(courseId),
                amount,
                paymentMethod,
                paymentDate
        );

        boolean isSave = paymentBO.save(paymentDTO);

        if (isSave) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }

    }

    @FXML
    void handleUpdatePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        String paymentId = txtPaymentId.getText();
        String studentId = cbStudentID.getValue().toString();
        String courseId = cbCourseid.getValue().toString();
        String amount = txtAmount.getText();
        String paymentMethod = cbMethod.getValue().toString();
        String paymentDate = dpPaymentDate.getValue().toString();

        PaymentDTO paymentDTO = new PaymentDTO(
                Long.valueOf(paymentId),
                Long.valueOf(studentId),
                Long.valueOf(courseId),
                amount,
                paymentMethod,
                paymentDate
        );

        boolean isUpdate = paymentBO.update(paymentDTO);
        if (isUpdate) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }

    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        PaymentDTO selectedItem = tblPayment.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtPaymentId.setText(String.valueOf(selectedItem.getPaymentId()));
            txtAmount.setText(String.valueOf(selectedItem.getAmount()));
            dpPaymentDate.setValue(LocalDate.parse(selectedItem.getPaymentDate()));
            cbStudentID.setValue(selectedItem.getStudentId());
            cbCourseid.setValue(selectedItem.getCourseId());
            cbMethod.setValue(selectedItem.getPaymentMethod());
        }
    }

}
