package com.kevguev.starter_template.services.models;

import lombok.Data;

@Data
public class User {
    public String firstName;
    public String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
