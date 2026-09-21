package com.ers.service;

import com.ers.dao.IUserDao;
import com.ers.model.User;

import java.util.List;

public class UserServiceImpl implements IUserService{
    private IUserDao userDao;
    public UserServiceImpl(IUserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public boolean deleteUserById(int userId) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean updateUserStatus(int userId, boolean active) {
        return false;
    }
}
