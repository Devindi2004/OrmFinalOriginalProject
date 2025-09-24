package org.example.ormfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class LessonDTO {
    private long lessonId;
    private Date date;
    private String time;
    private String status;
    private long courseId;
    private long instructorId;
    private long studentId;

    // Constructor without lessonId for saving new lessons
    public LessonDTO(Date date, String time, String status, long courseId, long instructorId, long studentId) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.studentId = studentId;
    }
}