package com.kevguev.starter_template.controllers;

import com.kevguev.starter_template.controllers.resources.UserResource;
import com.kevguev.starter_template.services.interfaces.UserService;
import com.kevguev.starter_template.services.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getUsers(@RequestParam(required = false) String lastName) {
        List<User> users;
        if (lastName != null) {
            users = userService.retrieveUsers(lastName);
        } else {
            users = userService.retrieveUsers();
        }

        return ResponseEntity.ok(convertToUserResources(users));
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody UserResource userResource) {
        User user = userService.createUser(new User(userResource.firstName, userResource.lastName));

        return ResponseEntity.ok(new UserResource(user));
    }

    private static List<UserResource> convertToUserResources(List<User> users) {
        List<UserResource> userResources = new ArrayList<>();
        for (User user : users) {
            UserResource userResource = new UserResource(user);
            userResources.add(userResource);
        }

        return userResources;
    }
}
