package com.kevguev.starter_template.data.models;

import com.kevguev.starter_template.services.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class UserModel {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public UserModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserModel(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
