package com.ers.service;

import com.ers.dao.IUserDao;
import com.ers.model.User;

import java.util.List;

public class UserServiceImpl implements IUserService{
     IUserDao userDao;
     public  UserServiceImpl(){
         this.userDao=new  com.ers.dao.UserDaoImpl();
     }

    @Override
    public User registerUser(User user) {
        User existing=userDao.getUserByUsername(user.getUserName());
        if(existing !=null)
        return null;
        boolean status=userDao.registerUser(user);
        if(status){
            return  userDao.getUserByUsername(user.getUserName());
        }
        return null;
    }

    @Override
    public User loginUser(String username, String password) {
        User user=userDao.getUserByUsername(username);
        if(user !=null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
