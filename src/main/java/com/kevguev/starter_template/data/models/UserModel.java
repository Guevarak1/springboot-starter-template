package com.kevguev.starter_template.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserModel {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public UserModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
