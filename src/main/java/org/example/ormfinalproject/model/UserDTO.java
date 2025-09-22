package org.example.ormfinalproject.model;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString

public class UserDTO {
    private long userId;
    private String name;
    private String password;
    private String role;

    public UserDTO(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
