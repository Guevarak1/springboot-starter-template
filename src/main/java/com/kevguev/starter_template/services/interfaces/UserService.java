package com.kevguev.starter_template.services.interfaces;

import com.kevguev.starter_template.services.models.User;

import java.util.List;

public interface UserService {

    List<User> retrieveUsers();

    List<User> retrieveUsers(String lastName);

    User createUser(User user);
}
