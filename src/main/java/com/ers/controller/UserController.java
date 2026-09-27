package com.ers.controller;

import com.ers.model.User;
import com.ers.service.IUserService;
import com.ers.service.UserServiceImpl;

public class UserController {

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
