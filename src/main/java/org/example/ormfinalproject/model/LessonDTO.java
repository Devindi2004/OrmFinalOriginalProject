package org.example.ormfinalproject.model;

public class LessonDTO {
private int lessonId;
private String date;
private String time;
private String status;
private int studentId;
private int courseId;
private int instructorId;

    public LessonDTO(int lessonId, String date, String time, String status, int studentId, int courseId, int instructorId) {
        this.lessonId = lessonId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
        this.instructorId = instructorId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
