package com.ers.controller;

import com.ers.dao.EmployeeDaoImpl;
import com.ers.dao.IEmployeeDao;
import com.ers.dao.UserDaoImpl;
import com.ers.model.*;
import com.ers.dao.IUserDao;
import com.ers.service.EmployeeServiceImpl;
import com.ers.service.IEmployeeService;
import com.ers.service.IUserService;
import com.ers.service.UserServiceImpl;
import com.ers.util.JDBCUtil;
import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Loggere;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        EmployeeController empController = new EmployeeController();
        DepartmentController depController = new DepartmentController();
        ExpenseCategoryController expCategoryController = new ExpenseCategoryController();
        ExpenseClaimController claimController = new ExpenseClaimController();

        log.info("Expense Reimbursement System started");

        while (true) {
            System.out.println("\n=== Expense Reimbursement System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Department Operations");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter role: ");
                    String role = sc.nextLine();

                    User newUser = new User();
                    newUser.setUserName(username);
                    newUser.setPassword(password);
                    newUser.setRole(role);
                    newUser.setActive(true);
                    newUser.setCreatedAt(LocalDateTime.now());

                    User registered = userController.registerUser(newUser);

                    if (registered != null) {
                        System.out.println("Registration Success! UserId: " + registered.getUserId());
                        log.info("User registered successfully. User ID: {}", registered.getUserId());
                    } else {
                        System.out.println("Registration Failed! Username already exists.");
                        log.warn("Registration failed for username: {}", username);
                    }
                } else if (choice == 2) {
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    User loggedIn = userController.loginUser(username, password);

                    if (loggedIn != null) {
                        System.out.println("Login Success! Welcome " + loggedIn.getUserName() + " Role: " + loggedIn.getRole());
                        log.info("Login successful. Username: {}, Role: {}", loggedIn.getUserName(), loggedIn.getRole());

                        if (loggedIn.getRole().equalsIgnoreCase("EMPLOYEE")) {
                            showEmployeeMenu(sc, empController, expCategoryController, claimController, loggedIn);
                        }
                    } else {
                        System.out.println("Login Failed! Check username/password");
                        log.warn("Login failed for username: {}", username);
                    }
                } else if (choice == 3) {
                    showDepartmentMenu(sc, depController);
                } else if (choice == 4) {
                    System.out.println("Exiting...");
                    log.info("Expense Reimbursement System stopped");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                    log.warn("Invalid main menu choice: {}", choice);
                }
            } catch (Exception e) {
                log.error("Error in main menu", e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public static void showEmployeeMenu(Scanner sc, EmployeeController empController, ExpenseCategoryController expCategoryController, ExpenseClaimController claimController, User loggedInUser) {
        while (true) {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View My Profile");
            System.out.println("2. View All Employees");
            System.out.println("3. Expense Category Operations");
            System.out.println("4. Expense Claim Operations");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            try {
                int ch = sc.nextInt();
                sc.nextLine();

                if (ch == 1) {
                    Employee existing = empController.getEmployeeByUserId(loggedInUser.getUserId());

                    if (existing != null) {
                        System.out.println("Your Profile: " + existing.getEmployeeId() + " | " + existing.getFullName() + " | " + existing.getEmail());
                        log.info("Employee profile viewed. Employee ID: {}", existing.getEmployeeId());
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

                        if (saved != null) {
                            System.out.println("INSERTED SUCCESS! ID: " + saved.getEmployeeId());
                            log.info("Employee inserted successfully. Employee ID: {}", saved.getEmployeeId());
                        } else {
                            System.out.println("INSERT FAILED");
                            log.warn("Employee insert failed for User ID: {}", loggedInUser.getUserId());
                        }
                    }
                } else if (ch == 2) {
                    List<Employee> employees = empController.getAllEmployees();
                    employees.forEach(e -> System.out.println(e.getEmployeeId() + " | " + e.getFullName() + " | " + e.getEmail()));
                    log.info("All employees viewed");
                } else if (ch == 3) {
                    showExpenseCategoryMenu(sc, expCategoryController);
                } else if (ch == 4) {
                    showExpenseClaimMenu(sc, claimController);
                } else if (ch == 5) {
                    System.out.println("Logout successful.");
                    log.info("User logged out: {}", loggedInUser.getUserName());
                    break;
                } else {
                    System.out.println("Invalid choice.");
                    log.warn("Invalid employee menu choice: {}", ch);
                }
            } catch (Exception e) {
                log.error("Error in Employee Menu", e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showExpenseCategoryMenu(Scanner sc, ExpenseCategoryController expCatController) {
        while (true) {
            System.out.println("\n--- EXPENSE CATEGORY MENU ---");
            System.out.println("1. Add Expense Category");
            System.out.println("2. View Category By ID");
            System.out.println("3. View All Categories");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter Category Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();

                    ExpenseCategory cat = new ExpenseCategory();
                    cat.setCategory_name(name);
                    cat.setDescription(desc);

                    ExpenseCategory result = expCatController.addExpenseCategory(cat);

                    if (result != null) {
                        System.out.println("Category Added Successfully!");
                        log.info("Expense category added successfully: {}", name);
                    } else {
                        System.out.println("Failed to Add Category.");
                        log.warn("Failed to add expense category: {}", name);
                    }
                } else if (choice == 2) {
                    System.out.print("Enter Category ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    ExpenseCategory cat = expCatController.getExpenseCategoryById(id);

                    if (cat != null) {
                        System.out.println("ID: " + cat.getCategory_id() + " | Name: " + cat.getCategory_name() + " | Desc: " + cat.getDescription());
                        log.info("Expense category viewed. Category ID: {}", id);
                    } else {
                        System.out.println("Category Not Found");
                        log.warn("Expense category not found. Category ID: {}", id);
                    }
                } else if (choice == 3) {
                    List<ExpenseCategory> list = expCatController.getAllExpenseCategories();
                    System.out.println("--- All Categories ---");

                    for (ExpenseCategory c : list) {
                        System.out.println(c.getCategory_id() + " | " + c.getCategory_name() + " | " + c.getDescription());
                    }
                    log.info("All expense categories viewed");
                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                    log.warn("Invalid expense category menu choice: {}", choice);
                }
            } catch (Exception e) {
                log.error("Error in Expense Category Menu", e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showExpenseClaimMenu(Scanner sc, ExpenseClaimController claimController) {
        while (true) {
            System.out.println("\n--- EXPENSE CLAIM MENU ---");
            System.out.println("1. Submit Claim");
            System.out.println("2. View My Claims");
            System.out.println("3. View All Claims");
            System.out.println("4. Approve/Reject Claim");
            System.out.println("5. Back");
            System.out.print("Enter Choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    ExpenseClaim claim = new ExpenseClaim();

                    System.out.print("Enter Employee ID: ");
                    int employeeId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    claim.setEmployeeId(employeeId);
                    claim.setClaimDesc(description);
                    claim.setClaimAmount(amount);
                    claim.setClaimDate(LocalDate.now());
                    claim.setStatus("PENDING");
                    claim.setDocumentPath("bill.pdf");

                    if (claimController.submitClaim(claim)) {
                        System.out.println("Claim Submitted Successfully!");
                        log.info("Claim submitted successfully. Employee ID: {}, Amount: {}", employeeId, amount);
                    } else {
                        System.out.println("Claim Submission Failed!");
                        log.warn("Claim submission failed. Employee ID: {}", employeeId);
                    }
                } else if (choice == 2) {
                    System.out.print("Enter Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine();

                    List<ExpenseClaim> myList = claimController.viewMyClaims(empId);

                    if (myList == null || myList.isEmpty()) {
                        System.out.println("No Claims Found.");
                        log.info("No claims found for Employee ID: {}", empId);
                    } else {
                        System.out.println("--- My Claims ---");
                        myList.forEach(System.out::println);
                        log.info("Claims viewed for Employee ID: {}", empId);
                    }
                } else if (choice == 3) {
                    List<ExpenseClaim> allList = claimController.viewAllClaims();

                    if (allList == null || allList.isEmpty()) {
                        System.out.println("No Claims Found.");
                        log.info("No expense claims found");
                    } else {
                        System.out.println("--- All Claims ---");
                        allList.forEach(System.out::println);
                        log.info("All expense claims viewed");
                    }
                } else if (choice == 4) {
                    System.out.print("Enter Claim ID to Approve/Reject: ");
                    int claimId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Status (APPROVED/REJECTED): ");
                    String status = sc.nextLine();

                    if (claimController.approveOrRejectClaim(claimId, status)) {
                        System.out.println("Status Updated Successfully!");
                        log.info("Claim status updated. Claim ID: {}, Status: {}", claimId, status);
                    } else {
                        System.out.println("Status Update Failed!");
                        log.warn("Claim status update failed. Claim ID: {}", claimId);
                    }
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("Invalid Choice.");
                    log.warn("Invalid expense claim menu choice: {}", choice);
                }
            } catch (Exception e) {
                log.error("Error in Expense Claim Menu", e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showDepartmentMenu(Scanner sc, DepartmentController depController) {
        while (true) {
            System.out.println("\n--- DEPARTMENT MENU ---");
            System.out.println("1. Add Department");
            System.out.println("2. View All Departments");
            System.out.println("3. View Department By Id");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            try {
                int ch = sc.nextInt();
                sc.nextLine();

                if (ch == 1) {
                    System.out.print("Enter Department Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Manager ID: ");
                    int managerId = sc.nextInt();
                    sc.nextLine();

                    Department dept = new Department();
                    dept.setDepartmentName(name);
                    dept.setManagerId(managerId);

                    Department result = depController.addDepartment(dept);

                    if (result != null) {
                        System.out.println("Department Inserted Successfully!");
                        log.info("Department inserted successfully: {}", name);
                    } else {
                        System.out.println("Failed to Insert Department");
                        log.warn("Department insertion failed: {}", name);
                    }
                } else if (ch == 2) {
                    depController.getAllDepartments().forEach(d -> System.out.println(d.getDepartmentId() + " | " + d.getDepartmentName() + " | " + d.getManagerId()));
                    log.info("All departments viewed");
                } else if (ch == 3) {
                    System.out.print("Enter Department ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Department d = depController.getDepartmentById(id);

                    if (d != null) {
                        System.out.println(d.getDepartmentId() + " | " + d.getDepartmentName() + " | " + d.getManagerId());
                        log.info("Department viewed. Department ID: {}", id);
                    } else {
                        System.out.println("Department not found.");
                        log.warn("Department not found. Department ID: {}", id);
                    }
                } else if (ch == 4) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                    log.warn("Invalid department menu choice: {}", ch);
                }
            } catch (Exception e) {
                log.error("Error in Department Menu", e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }
    }

