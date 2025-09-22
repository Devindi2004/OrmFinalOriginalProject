package org.example.ormfinalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PaymentController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private HBox inputHBox;

    @FXML
    private TableView<?> tblPayments;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMethod;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtStudentId;

    @FXML
    void handleAddPayment(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDeletePayment(ActionEvent event) {

    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {

    }

}
