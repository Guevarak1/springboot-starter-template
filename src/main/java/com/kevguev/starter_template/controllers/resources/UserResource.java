package com.kevguev.starter_template.controllers.resources;

import com.kevguev.starter_template.services.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResource {
    private String id;
    private String firstName;
    private String lastName;
    private AddressResource address;

    public UserResource(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress() != null ? new AddressResource(user.getAddress()) : null;
    }
}
