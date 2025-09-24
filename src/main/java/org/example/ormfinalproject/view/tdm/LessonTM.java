package org.example.ormfinalproject.view.tdm;
import lombok.*;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LessonTM {
    private long lessonID;
    private Date date;
    private String time;
    private String status;
    private long studentID;
    private long courseID;
    private long instructorID;
}