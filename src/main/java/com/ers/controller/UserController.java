package com.ers.controller;

import com.ers.model.User;
import com.ers.service.IUserService;
import com.ers.service.UserServiceImpl;

public class UserController {
    //private IUserService userService;
   // public UserController(IUserService userService) {
    // this.userService = userService;}public User addUser(User user) {return null;}
    //public boolean updateUser(User user) {return false;}
    //  public User getUserById(int userId) {return null;}
   // public List<User> getAllUsers() {return null;}
    //public boolean deleteUserById(int userId) {return false;}
    //public User getUserByUsername(String username) {return null;}
   // public boolean updateUserStatus(int userId, boolean active) {return false;}

   private  IUserService userService;
   public  UserController(){
       this.userService=new UserServiceImpl();
   }

   public User registerUser(User user){
       return userService.registerUser(user);
   }

   public User loginUser(String username,String password){
       return userService.loginUser(username,password);
   }
}
