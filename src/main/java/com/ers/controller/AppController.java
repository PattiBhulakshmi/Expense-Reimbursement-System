package com.ers.controller;

import com.ers.dao.EmployeeDaoImpl;
import com.ers.dao.IEmployeeDao;
import com.ers.dao.UserDaoImpl;
import com.ers.model.User;
import com.ers.model.Employee;
import com.ers.dao.IUserDao;
import com.ers.service.EmployeeServiceImpl;
import com.ers.service.IEmployeeService;
import com.ers.service.IUserService;
import com.ers.service.UserServiceImpl;
import com.ers.util.JDBCUtil;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AppController {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);


        UserController userController = new UserController();

        JDBCUtil jdbcUtil = new JDBCUtil();
        IEmployeeDao employeeDao = new EmployeeDaoImpl(jdbcUtil);
        IEmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
        EmployeeController empController = new EmployeeController(employeeService);

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

                    if (loggedIn.getRole().equalsIgnoreCase("EMPLOYEE")) {
                        showEmployeeMenu(sc, empController, loggedIn);
                    }

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

    public static void showEmployeeMenu(Scanner sc, EmployeeController empController, User loggedInUser) {
        while (true) {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View My Profile");
            System.out.println("2. View All Employees");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {

                Employee existing = empController.getEmployeeByUserId(loggedInUser.getUserId());
                if (existing != null) {
                    System.out.println("Your Profile: " + existing.getEmployeeId() + " | " + existing.getFullName() + " | " + existing.getEmail());
                } else {
                    System.out.println("No profile found. Let's INSERT your details:");
                    System.out.print("Enter Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Department ID (1-IT, 2-HR, 3-Finance): ");
                    int dept = sc.nextInt();
                    sc.nextLine();

                    Employee emp = new Employee();
                    emp.setUserId(loggedInUser.getUserId());
                    emp.setFullName(name);
                    emp.setEmail(email);
                    emp.setDepartmentId(dept);

                    Employee saved = empController.addNewEmployee(emp);
                    if (saved != null) System.out.println("INSERTED SUCCESS! ID: " + saved.getEmployeeId());
                    else System.out.println("INSERT FAILED - check DB connection");
                }

            } else if (ch == 2) {
                empController.getAllEmployees().forEach(e ->
                        System.out.println(e.getEmployeeId() + " | " + e.getFullName() + " | " )
                );

            } else if (ch == 3) {
                break;
            }
        }
    }
}
