package com.ers;

import com.ers.dao.IUserDao;
import com.ers.model.User;
import com.ers.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private IUserDao userDao;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userDao = mock(IUserDao.class);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    void testRegisterUserSuccess() {

        User user = new User();

        when(userDao.getUserByUsername(user.getUserName()))
                .thenReturn(null).thenReturn(user);

        when(userDao.registerUser(user)).thenReturn(true);

        User result = userService.registerUser(user);

        assertNotNull(result);
        assertEquals(user, result);

        Mockito.verify(userDao).registerUser(user);
    }

    @Test
    void testRegisterUserAlreadyExists() {

        User user = new User();

        when(userDao.getUserByUsername(user.getUserName())).thenReturn(user);

        User result = userService.registerUser(user);

        assertNull(result);

        Mockito.verify(userDao).registerUser(user);
    }

    @Test
    void testRegisterUserFailed() {

        User user = new User();

        when(userDao.getUserByUsername(user.getUserName()))
                .thenReturn(null);

        when(userDao.registerUser(user)).thenReturn(false);

        User result = userService.registerUser(user);

        assertNull(result);

       Mockito. verify(userDao).registerUser(user);
    }

    @Test
    void testLoginSuccess() {

        String username = "admin";
        String password = "1234";

        User user = mock(User.class);

        when(user.getPassword()).thenReturn(password);

        when(userDao.getUserByUsername(username))
                .thenReturn(user);

        User result = userService.loginUser(username, password);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void testLoginWrongPassword() {

        String username = "admin";

        User user = mock(User.class);

        when(user.getPassword()).thenReturn("1234");

        when(userDao.getUserByUsername(username)).thenReturn(user);

        User result = userService.loginUser(username, "wrong");

        assertNull(result);
    }

    @Test
    void testLoginUserNotFound() {

        String username = "admin";

        when(userDao.getUserByUsername(username)).thenReturn(null);

        User result = userService.loginUser(username, "1234");

        assertNull(result);
    }
}
