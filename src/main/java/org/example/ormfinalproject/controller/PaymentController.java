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
import org.example.ormfinalproject.BO.custom.PaymentBO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PaymentController {

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
    private ComboBox<String> cbCourseid;

    @FXML
    private ComboBox<String> cbMethod;

    @FXML
    private ComboBox<String> cbStudentID;

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
    private TextField txtPaymentId;

    public void initialize() {
        loadAllPayments();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        clmPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        clmStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        clmCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        clmPDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmPaymenMethodtId.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        loadAllPayments();

        cbMethod.setItems(FXCollections.observableArrayList("Cash", "Card").sorted());
        loadStudentIds();
        loadCourseIds();
    }

    private void loadAllPayments() {
        try {
            List<PaymentDTO> all = paymentBO.getAll();
            ObservableList<PaymentDTO> list = FXCollections.observableArrayList();
            for (PaymentDTO dto : all) {
                list.add(new PaymentDTO(
                        dto.getPaymentId(),
                        dto.getPaymentDate(),
                        dto.getPaymentMethod(),
                        dto.getAmount(),
                        dto.getStudentId(),
                        dto.getCourseId()
                ));
            }
            tblPayment.setItems(list);
        } catch (Exception e) {
            showError("Error loading payments: " + e.getMessage());
        }
    }

    @FXML
    void handleBackButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtPaymentId.clear();
        dpPaymentDate.setValue(null);
        txtAmount.clear();
        cbMethod.setValue(null);
        cbStudentID.setValue(null);
        cbCourseid.setValue(null);
    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
        try {
            String id = txtPaymentId.getText();
            if (id.isEmpty()) {
                showError("Please select a payment to delete!");
                return;
            }

            if (paymentBO.deletePayment(id)) {
                showInfo("Payment deleted successfully!");
                loadAllPayments();
                clearFields();
            } else {
                showError("Payment not found or cannot be deleted!");
            }
        } catch (Exception e) {
            showError("Error deleting payment: " + e.getMessage());
        }
    }

    @FXML
    void handleSavePayment(ActionEvent event) {
        try {
            Date date = Date.valueOf(dpPaymentDate.getValue());
            String method = cbMethod.getSelectionModel().getSelectedItem();
            String amount = txtAmount.getText();
            String studentId = cbStudentID.getSelectionModel().getSelectedItem(); // FIX
            String courseId = cbCourseid.getSelectionModel().getSelectedItem();   // FIX
            System.out.println(date + " " + method + " " + amount + " " + studentId + " " + courseId);
            PaymentDTO dto = new PaymentDTO(
                    date,
                    studentId,
                    courseId,
                    method,
                    amount
            );

            System.out.println(dto + " controller");
            if (paymentBO.savePayment(dto)) {
                showInfo("Payment added successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving payment: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {
        try {
            long id = Long.parseLong(txtPaymentId.getText());
            PaymentDTO dto = new PaymentDTO(
                    Date.valueOf(dpPaymentDate.getValue()),
                    cbStudentID.getSelectionModel().getSelectedItem(),
                    cbCourseid.getSelectionModel().getSelectedItem(),
                    cbMethod.getSelectionModel().getSelectedItem(),
                    txtAmount.getText()
            );
            dto.setPaymentId(id);

            if (paymentBO.updatePayment(dto)) {
                showInfo("Payment updated successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating payment: " + e.getMessage());
        }
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        PaymentDTO selected = tblPayment.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtPaymentId.setText(String.valueOf(selected.getPaymentId()));
            dpPaymentDate.setValue(LocalDate.parse(selected.getPaymentDate()));
            txtAmount.setText(selected.getAmount());
            cbMethod.getSelectionModel().select(selected.getPaymentMethod()); // FIX
            cbStudentID.getSelectionModel().select(String.valueOf(selected.getStudentId())); // FIX
            cbCourseid.getSelectionModel().select(String.valueOf(selected.getCourseId()));   // FIX
        }
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

    private void loadStudentIds() {
        try {
            List<String> ids = paymentBO.getAllStudentIds();
            cbStudentID.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseIds() {
        try {
            List<String> ids = paymentBO.getAllCourseIds();
            cbCourseid.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
