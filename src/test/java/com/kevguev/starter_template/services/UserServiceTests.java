package com.kevguev.starter_template.services;


import com.kevguev.starter_template.data.models.UserModel;
import com.kevguev.starter_template.data.repositories.interfaces.UserRepository;
import com.kevguev.starter_template.exceptions.UserNotFoundException;
import com.kevguev.starter_template.services.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/*
    naming convention
    MethodName_StateUnderTest_ExpectedBehavior

    isAdult_AgeLessThan18_False
    withdrawMoney_InvalidAccount_ExceptionThrown
    admitStudent_MissingMandatoryFields_FailToAdmit
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void retrieveUsers_WithUsers_ShouldRetrieveUsers() {
        //setup
        when(repository.findAll()).thenReturn(getUserModels());

        //test method
        List<User> users = userService.retrieveUsers();

        //verify expected results
        assertEquals(3, users.size());
    }

    @Test
    public void retrieveUser_UserExists_UserRetrieved() {
        when(repository.findById(anyString())).thenReturn(Optional.of(new UserModel("first", "last")));

        User user = userService.retrieveUser("testId");

        assertEquals("first", user.getFirstName());
        assertEquals("last", user.getLastName());
    }

    @Test(expected = UserNotFoundException.class)
    public void retrieveUser_UserDoesNotExists_UserNotFoundExceptionThrown() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        userService.retrieveUser("testId");
    }

    @Test
    public void retrieveUsersByLastName_WithUsersWithLastName_RetrievesUsersByLastName() {
        List<UserModel> userModels = new ArrayList<>();
        userModels.add(new UserModel("testUser", "testLastName"));
        when(repository.findByLastName(anyString())).thenReturn(userModels);

        List<User> users = userService.retrieveUsers("testLastName");

        assertEquals("testLastName", users.get(0).getLastName());
    }

    @Test
    public void retrieveUsersByLastName_WithNoUsersWithLastName_RetrievesNoUsers() {
        when(repository.findByLastName(anyString())).thenReturn(new ArrayList<>());

        List<User> users = userService.retrieveUsers("lastName");

        assertEquals(0, users.size());
    }

    @Test
    public void createUser_UserDoesNotExist_CreatesUser() {
        when(repository.save(any(UserModel.class))).thenReturn(new UserModel("test", "user"));

        User user = userService.createUser(new User("test", "user"));

        assertEquals("test", user.getFirstName());
        assertEquals("user", user.getLastName());
    }

    @Test
    public void deleteUser_UserExists_DeletesUser() {
        when(repository.existsById(anyString())).thenReturn(true);

        userService.deleteUserById("userId");

        verify(repository, times(1)).deleteById("userId");
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUser_UserDoesNotExists_UserNotFoundExceptionThrown() {
        when(repository.existsById(anyString())).thenReturn(false);

        userService.deleteUserById("userId");
    }

    private static List<UserModel> getUserModels() {
        List<UserModel> userModels = new ArrayList<>();
        userModels.add(new UserModel("testUser", "testLastName"));
        userModels.add(new UserModel("testUser2", "testLastName"));
        userModels.add(new UserModel("testUser3", "testLast"));

        return userModels;
    }
}
