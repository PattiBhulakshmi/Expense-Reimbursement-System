package com.ers.controller;

import com.ers.model.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AppController {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        UserController userController = new UserController();

        while (true){
            System.out.println("\n=== Expense Reimbursement System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice==1){
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                System.out.print("Enter role: ");
                String role = sc.nextLine();

                User newUser=new User();
                newUser.setUserName(username);
                newUser.setPassword(password);
                newUser.setRole(role);
                newUser.setActive(true);
                newUser.setCreatedAt(LocalDateTime.now());

                User registered=userController.registerUser(newUser);
                if(registered!=null){
                    System.out.println("Registration Success! UserId: " + registered.getUserId());
                }else {
                    System.out.println("Registration Failed! Username already exists.");
                }
            } else if (choice==2) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                User loggedIn = userController.loginUser(username, password);
                if (loggedIn != null) {
                    System.out.println("Login Success! Welcome " + loggedIn.getUserName() + " Role: " + loggedIn.getRole());
                } else {
                    System.out.println("Login Failed! Check username/password");
                }

            }else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
        }
      sc.close();
    }
}
