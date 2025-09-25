package org.example.ormfinalproject.model;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InstructorDTO{
    private long instructorId;
    private String name;
    private String email;
    private String phone;
    private String availability;

    public InstructorDTO(String name, String email, String phone,String availability) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.availability = availability;
    }



}