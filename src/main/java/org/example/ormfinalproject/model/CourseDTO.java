package org.example.ormfinalproject.model;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CourseDTO {
    private long courseId;
    private String name;
    private String duration;
    private String fee;

    public CourseDTO(String name, String duration, String fee) {
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }
}
