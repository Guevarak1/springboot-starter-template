package com.kevguev.starter_template.controllers.resources;

import com.kevguev.starter_template.services.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResource {
    public String firstName;
    public String lastName;

    public UserResource(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
    }
}
