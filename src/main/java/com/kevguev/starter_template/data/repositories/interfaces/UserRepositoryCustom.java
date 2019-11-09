package com.kevguev.starter_template.data.repositories.interfaces;

import com.kevguev.starter_template.services.models.User;

public interface UserRepositoryCustom {
    User updateUser(String id, User user);
}
