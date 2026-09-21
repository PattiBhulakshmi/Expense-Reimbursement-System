package com.ers.dao;

import com.ers.model.User;

import java.util.List;

public interface IUserDao {
    User addUser(User user);
    boolean updateUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    boolean deleteUserById(int userId);
    User getUserByUsername(String username);
    boolean updateUserStatus(int userId, boolean active);
}
