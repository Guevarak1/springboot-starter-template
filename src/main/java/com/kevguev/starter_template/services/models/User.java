package com.kevguev.starter_template.services.models;

import com.kevguev.starter_template.controllers.resources.UserResource;
import com.kevguev.starter_template.data.models.UserModel;
import lombok.Data;

@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Address address;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(UserResource userResource) {
        this.id = userResource.getId();
        this.firstName = userResource.getFirstName();
        this.lastName = userResource.getLastName();
        this.address = new Address(userResource.getAddress());
    }

    public User(UserModel userModel) {
        this.id = userModel.getId();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.address = new Address(userModel.getAddress());
    }
}
