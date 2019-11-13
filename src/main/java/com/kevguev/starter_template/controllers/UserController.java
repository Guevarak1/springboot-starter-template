package com.kevguev.starter_template.controllers;

import com.kevguev.starter_template.controllers.resources.AddressResource;
import com.kevguev.starter_template.controllers.resources.UserResource;
import com.kevguev.starter_template.services.interfaces.UserService;
import com.kevguev.starter_template.services.models.Address;
import com.kevguev.starter_template.services.models.User;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable String id) {
        User user = userService.retrieveUser(id);

        return ResponseEntity.ok(new UserResource(user));
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
        User user = userService.createUser(new User(userResource));

        return new ResponseEntity<>(new UserResource(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable String id, @RequestBody UserResource userResource) {
        User updatedUser = userService.updateUser(id, new User(userResource));

        return ResponseEntity.ok(new UserResource(updatedUser));
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<UserResource> updateUsersAddress(@PathVariable String id, @RequestBody AddressResource addressResource) {
        User updatedUser = userService.updateUsersAddress(id, new Address(addressResource));

        return ResponseEntity.ok(new UserResource(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
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
