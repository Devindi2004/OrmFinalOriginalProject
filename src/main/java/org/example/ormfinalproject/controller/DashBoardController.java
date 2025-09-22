package org.example.ormfinalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.ormfinalproject.BO.custom.*;
import org.example.ormfinalproject.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashBoardController {

    @FXML
    private AnchorPane Ank1;

    @FXML
    private Button btnManageCourses;

    @FXML
    private Button btnManageInstructors;

    @FXML
    private Button btnManageLessons;

    @FXML
    private Button btnManagePayments;

    @FXML
    private Button btnManageStudents;

    @FXML
    private Button btnManageUsers;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnLogout;

    @FXML
    private VBox courseId;

    @FXML
    private Label dSchool;

    @FXML
    private VBox instructorId;

    @FXML
    private Label lblTotalCourses;

    @FXML
    private Label lblTotalInstructors;

    @FXML
    private Label lblTotalLessons;

    @FXML
    private Label lblTotalPayments;

    @FXML
    private Label lblTotalStudents;

    @FXML
    private Label lblTotalUsers;

    @FXML
    private VBox lessonId;

    @FXML
    private VBox paymentId;

    @FXML
    private VBox studentId;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox userId;

    @FXML
    private VBox v1;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private final InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSTRUCTOR);
    private final LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LESSON);
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private final CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    public void initialize() {
        try {
            setStudent();
            setInstructor();
            setCourse();
            setLesson();
            setPayment();
            setUser("Guest"); // Default username if not set
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error", "Failed to load dashboard data: " + e.getMessage());
        }
    }

    @FXML
    void handleManageCourses(ActionEvent event) {
        navigateTo("/view/course.fxml");
    }

    @FXML
    void handleManageInstructors(ActionEvent event) {
        navigateTo("/view/instructor_Manage.fxml");
    }

    @FXML
    void handleManageLessons(ActionEvent event) {
        navigateTo("/view/lesson_Manage.fxml");
    }

    @FXML
    void handleManagePayments(ActionEvent event) {
        navigateTo("/view/payment_Manage.fxml");
    }

    @FXML
    void handleManageStudents(ActionEvent event) {
        navigateTo("/view/student_Manage.fxml");
    }

    @FXML
    void handleManageUsers(ActionEvent event) {
        navigateTo("/view/user.fxml");
    }

    @FXML
    void handleSearch(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Search", "Search functionality not implemented.");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
            Scene loginScene = new Scene(loader.load(), 900, 600);
            Stage stage = (Stage) Ank1.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("DriveSmart Academy Login");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Logout Error", "Failed to load login page: " + e.getMessage());
        }
    }

    private void navigateTo(String fxmlPath) {
        try {
            Ank1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
            pane.prefWidthProperty().bind(Ank1.widthProperty());
            pane.prefHeightProperty().bind(Ank1.heightProperty());
            Ank1.getChildren().add(pane);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Page not found: " + fxmlPath);
        }
    }

    public void setStudent() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        lblTotalStudents.setText(String.valueOf(allStudent != null ? allStudent.size() : 0));
    }

    public void setInstructor() throws SQLException, ClassNotFoundException {
        ArrayList<InstructorDTO> allInstructor = instructorBO.getAllInstructor();
        lblTotalInstructors.setText(String.valueOf(allInstructor != null ? allInstructor.size() : 0));
    }

    public void setLesson() throws SQLException, ClassNotFoundException {
        ArrayList<LessonDTO> allLesson = lessonBO.getAllLesson();
        lblTotalLessons.setText(String.valueOf(allLesson != null ? allLesson.size() : 0));
    }

    public void setPayment() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPayment = paymentBO.getAllPayment();
        lblTotalPayments.setText(String.valueOf(allPayment != null ? allPayment.size() : 0));
    }

    public void setCourse() throws SQLException, ClassNotFoundException {
        ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
        lblTotalCourses.setText(String.valueOf(allCourse != null ? allCourse.size() : 0));
    }

    public void setUser(String username) throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUser = userBO.getAllUser();
        lblTotalUsers.setText(String.valueOf(allUser != null ? allUser.size() : 0));
        dSchool.setText("Welcome, " + username + "!");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleLogin(String username) throws SQLException, ClassNotFoundException {
        setUser(username);
    }
}