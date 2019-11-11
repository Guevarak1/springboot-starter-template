package com.kevguev.starter_template.services;

import com.kevguev.starter_template.data.models.UserModel;
import com.kevguev.starter_template.data.repositories.interfaces.UserRepository;
import com.kevguev.starter_template.exceptions.UserNotFoundException;
import com.kevguev.starter_template.services.interfaces.UserService;
import com.kevguev.starter_template.services.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User retrieveUser(String id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (!userModel.isPresent())
            throw new UserNotFoundException("User could not be found with Id: " + id);

        return new User(userModel.get());
    }

    @Override
    public List<User> retrieveUsers() {
        return convertToUsers(userRepository.findAll());
    }

    @Override
    public List<User> retrieveUsers(String lastName) {
        return convertToUsers(userRepository.findByLastName(lastName));
    }

    @Override
    public User createUser(User user) {
        UserModel userModel = userRepository.save(new UserModel(user));
        return new User(userModel);
    }

    @Override
    public User updateUser(String id, User user) {
        return userRepository.updateUser(id, user);
    }

    @Override
    public void deleteUserById(String userId) {
        if (!userRepository.existsById(userId))
            throw new UserNotFoundException("User could not be found with Id: " + userId);

        userRepository.deleteById(userId);
    }

    private static List<User> convertToUsers(List<UserModel> userModels) {
        List<User> users = new ArrayList<>();
        for (UserModel userModel : userModels) {
            User user = new User(userModel);
            users.add(user);
        }

        return users;
    }
}
