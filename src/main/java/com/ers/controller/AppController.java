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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppController {

    private static final Logger logger=Logger.getLogger(AppController.class.getName());

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        UserController userController=new UserController();
        EmployeeController empController=new EmployeeController();
        DepartmentController depController=new DepartmentController();
        ExpenseCategoryController expCategoryController=new ExpenseCategoryController();
        ExpenseClaimController expClaimController=new ExpenseClaimController();
        ClaimItemController claimItemController=new ClaimItemController();
        FinanceExecutiveController finController=new FinanceExecutiveController();
        ReimbursementController reimbursementController=new ReimbursementController();

        logger.info("Expense Reimbursement System started");

        while(true){
            System.out.println("\n=== EXPENSE REIMBURSEMENT SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Department Operations");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try{
                int choice=sc.nextInt();
                sc.nextLine();

                if(choice==1){
                    System.out.print("Enter username: ");
                    String username=sc.nextLine();

                    System.out.print("Enter password: ");
                    String password=sc.nextLine();

                    System.out.print("Enter role (EMPLOYEE/FINANCE): ");
                    String role=sc.nextLine();

                    User newUser=new User();
                    newUser.setUserName(username);
                    newUser.setPassword(password);
                    newUser.setRole(role);
                    newUser.setActive(true);
                    newUser.setCreatedAt(LocalDateTime.now());

                    User registered=userController.registerUser(newUser);

                    if(registered!=null){
                        System.out.println("Registration Success! UserId: "+registered.getUserId());
                    }else{
                        System.out.println("Registration Failed! Username already exists.");
                    }

                }else if(choice==2){

                    System.out.print("Enter username: ");
                    String username=sc.nextLine();

                    System.out.print("Enter password: ");
                    String password=sc.nextLine();

                    User loggedIn=userController.loginUser(username,password);

                    if(loggedIn!=null){
                        System.out.println("Login Success! Welcome "+loggedIn.getUserName()+" Role: "+loggedIn.getRole());

                        if(loggedIn.getRole().equalsIgnoreCase("EMPLOYEE")){
                            showEmployeeMenu(sc,empController,expCategoryController,expClaimController,claimItemController,loggedIn);
                        }else if(loggedIn.getRole().equalsIgnoreCase("FINANCE")){
                            showFinanceMenu(sc,finController,expClaimController,reimbursementController,loggedIn);
                        }else{
                            System.out.println("Invalid role.");
                        }

                    }else{
                        System.out.println("Login Failed! Check username/password");
                    }

                }else if(choice==3){
                    showDepartmentMenu(sc,depController);

                }else if(choice==4){
                    System.out.println("Exiting...");
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in main menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }

        sc.close();
        logger.info("Expense Reimbursement System stopped");
    }

    public static void showEmployeeMenu(Scanner sc,EmployeeController empController,
                                        ExpenseCategoryController expCategoryController,
                                        ExpenseClaimController expClaimController,
                                        ClaimItemController claimItemController,
                                        User loggedInUser){

        while(true){
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View My Profile");
            System.out.println("2. View All Employees");
            System.out.println("3. Expense Category Operations");
            System.out.println("4. Expense Claim Operations");
            System.out.println("5. Claim Item Operations");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            try{
                int ch=sc.nextInt();
                sc.nextLine();

                if(ch==1){

                    Employee existing=empController.getEmployeeByUserId(loggedInUser.getUserId());

                    if(existing!=null){
                        System.out.println("Your Profile: "+existing.getEmployeeId()+" | "+existing.getFullName()+" | "+existing.getEmail());
                    }else{
                        System.out.println("No profile found. Let's INSERT your details:");

                        System.out.print("Enter Full Name: ");
                        String name=sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email=sc.nextLine();

                        System.out.print("Enter Department ID (1-IT, 2-HR, 3-Finance): ");
                        int dept=sc.nextInt();
                        sc.nextLine();

                        Employee emp=new Employee();
                        emp.setUserId(loggedInUser.getUserId());
                        emp.setFullName(name);
                        emp.setEmail(email);
                        emp.setDepartmentId(dept);

                        Employee saved=empController.addNewEmployee(emp);

                        if(saved!=null){
                            System.out.println("INSERTED SUCCESS! ID: "+saved.getEmployeeId());
                        }else{
                            System.out.println("INSERT FAILED");
                        }
                    }

                }else if(ch==2){
                  List<Employee> list=empController.getAllEmployees();
                    for (Employee e:list){
                        System.out.println(e.getEmployeeId()+" | "+e.getFullName()+" | "+e.getEmail());
                    }

                }else if(ch==3){

                    showExpenseCategoryMenu(sc,expCategoryController);

                }else if(ch==4){

                    Employee employee=empController.getEmployeeByUserId(loggedInUser.getUserId());

                    if(employee!=null){
                        showExpenseClaimMenu(sc,expClaimController,employee.getEmployeeId());
                    }else{
                        System.out.println("Create employee profile first.");
                    }

                }else if(ch==5){

                    Employee employee=empController.getEmployeeByUserId(loggedInUser.getUserId());

                    if(employee!=null){
                        showClaimItemMenu(sc,claimItemController);
                    }else{
                        System.out.println("Create employee profile first.");
                    }

                }else if(ch==6){
                    System.out.println("Logout successful.");
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Employee Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showExpenseCategoryMenu(Scanner sc,ExpenseCategoryController expCatController){

        while(true){
            System.out.println("\n--- EXPENSE CATEGORY MENU ---");
            System.out.println("1. Add Expense Category");
            System.out.println("2. View Category By ID");
            System.out.println("3. View All Categories");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            try{
                int choice=sc.nextInt();
                sc.nextLine();

                if(choice==1){

                    System.out.print("Enter Category Name: ");
                    String name=sc.nextLine();

                    System.out.print("Enter Description: ");
                    String desc=sc.nextLine();

                    ExpenseCategory cat=new ExpenseCategory();
                    cat.setCategory_name(name);
                    cat.setDescription(desc);

                    ExpenseCategory result=expCatController.addExpenseCategory(cat);

                    if(result!=null){
                        System.out.println("Category Added Successfully!");
                    }else{
                        System.out.println("Failed to Add Category.");
                    }

                }else if(choice==2){

                    System.out.print("Enter Category ID: ");
                    int id=sc.nextInt();
                    sc.nextLine();

                    ExpenseCategory cat=expCatController.getExpenseCategoryById(id);

                    if(cat!=null){
                        System.out.println("ID: "+cat.getCategory_id()+" | Name: "+cat.getCategory_name()+" | Desc: "+cat.getDescription());
                    }else{
                        System.out.println("Category Not Found");
                    }

                }else if(choice==3){

                    List<ExpenseCategory> list=expCatController.getAllExpenseCategories();

                    System.out.println("--- All Categories ---");

                    for(ExpenseCategory c:list){
                        System.out.println(c.getCategory_id()+" | "+c.getCategory_name()+" | "+c.getDescription());
                    }

                }else if(choice==4){
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Expense Category Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showExpenseClaimMenu(Scanner sc,ExpenseClaimController expClaimController,int employeeId){

        while(true){
            System.out.println("\n--- EXPENSE CLAIM MENU ---");
            System.out.println("1. Create Expense Claim");
            System.out.println("2. View My Claims");
            System.out.println("3. View Claim By ID");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            try{
                int ch=sc.nextInt();
                sc.nextLine();

                if(ch==1){

                    System.out.print("Enter Claim Description: ");
                    String description=sc.nextLine();

                    System.out.print("Enter Claim Amount: ");
                    double amount=sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Document Path: ");
                    String documentPath=sc.nextLine();

                    ExpenseClaim claim=new ExpenseClaim();
                    claim.setEmployeeId(employeeId);
                    claim.setClaimDesc(description);
                    claim.setClaimAmount(amount);
                    claim.setDocumentPath(documentPath);
                    claim.setStatus("PENDING");

                    ExpenseClaim result=expClaimController.submitClaim(claim);
                    if(result!=null){
                        System.out.println("Claim Created Successfully! Claim ID: "+result.getClaimId());
                    }else{
                        System.out.println("Claim Creation Failed.");
                    }

                }else if(ch==2){

                    List<ExpenseClaim> claims=expClaimController.viewMyClaims(employeeId);

                    for(ExpenseClaim claim:claims){
                        System.out.println(claim.getClaimId()+" | "+claim.getClaimDesc()+" | "+claim.getClaimAmount()+" | "+claim.getStatus());
                    }

                }else if(ch==3){

                    System.out.print("Enter Claim ID: ");
                    int claimId=sc.nextInt();
                    sc.nextLine();

                    ExpenseClaim claim=expClaimController.viewClaimById(claimId);

                    if(claim!=null){
                        System.out.println(claim.getClaimId()+" | "+claim.getClaimDesc()+" | "+claim.getClaimAmount()+" | "+claim.getStatus());
                    }else{
                        System.out.println("Claim not found.");
                    }

                }else if(ch==4){
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Expense Claim Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showClaimItemMenu(Scanner sc,ClaimItemController claimItemController){

        while(true){
            System.out.println("\n--- CLAIM ITEM MENU ---");
            System.out.println("1. Add Claim Item");
            System.out.println("2. View Items By Claim ID");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            try{
                int ch=sc.nextInt();
                sc.nextLine();

                if(ch==1){

                    System.out.print("Enter Claim ID: ");
                    int claimId=sc.nextInt();
                    System.out.print("Enter Category ID: ");
                    int categoryId=sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Description: ");
                    String description=sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount=sc.nextDouble();
                    sc.nextLine();

                    ClaimItem item=new ClaimItem();
                    item.setClaimId(claimId);
                    item.setCategoryId(categoryId);
                    item.setDescription(description);
                    item.setAmount(amount);

                    boolean result=claimItemController.addItem(item);

                    if(result){
                        System.out.println("Claim Item Added Successfully.");
                    }else{
                        System.out.println("Failed to Add Claim Item.");
                    }

                }else if(ch==2){

                    System.out.print("Enter Claim ID: ");
                    int claimId=sc.nextInt();
                    sc.nextLine();

                    List<ClaimItem> items=claimItemController.getItemsByClaimId(claimId);

                    for(ClaimItem item:items){
                        System.out.println(item.getItemId()+" | "+item.getClaimId()+" | "+item.getCategoryId()+" | "+item.getDescription()+" | "+item.getAmount());
                    }

                }else if(ch==3){
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Claim Item Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showFinanceMenu(Scanner sc,FinanceExecutiveController finController,
                                       ExpenseClaimController expClaimController,
                                       ReimbursementController reimbursementController,
                                       User loggedInUser){

        while(true){
            System.out.println("\n--- FINANCE MENU ---");
            System.out.println("1. View Finance Profile");
            System.out.println("2. View Pending Claims");
            System.out.println("3. Approve Claim");
            System.out.println("4. Reject Claim");
            System.out.println("5. Reimbursement Operations");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            try{
                int ch=sc.nextInt();
                sc.nextLine();

                if(ch==1){
                    FinanceExecutive finance=finController.getFinanceExecutiveById(loggedInUser.getUserId());
                    if(finance!=null){
                        System.out.println(finance.getEmployeeId()+" | "+finance.getFullName()+" | "+finance.getEmail());
                    }else {
                        System.out.println("No finance profile found. Let's INSERT your details:");
                        System.out.print("Enter Full Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        FinanceExecutive newFin = new FinanceExecutive();
                        newFin.setEmployeeId(loggedInUser.getUserId());
                        newFin.setFullName(name);
                        newFin.setEmail(email);
                        newFin.setDepartment("Finance");

                        FinanceExecutive saved = finController.addNewFinanceExecutive(newFin);
                        if (saved != null) {
                            System.out.println("FINANCE PROFILE INSERTED SUCCESS! ID: " + saved.getEmployeeId());
                        } else {
                            System.out.println("INSERT FAILED");
                        }
                    }

                }else if(ch==2){

                    List<ExpenseClaim> claims=expClaimController.viewAllClaims();

                    for(ExpenseClaim claim:claims){
                        System.out.println(claim.getClaimId()+" | Employee: "+claim.getEmployeeId()+" | "+claim.getClaimAmount()+" | "+claim.getStatus());
                    }

                }else if(ch==3){

                    System.out.print("Enter Claim ID: ");
                    int claimId=sc.nextInt();
                    sc.nextLine();

                    boolean result=expClaimController.approveOrRejectClaim(claimId,"Approved");

                    if(result){
                        System.out.println("Claim Approved Successfully.");
                    }else{
                        System.out.println("Claim Approval Failed.");
                    }

                }else if(ch==4){

                    System.out.print("Enter Claim ID: ");
                    int claimId=sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Rejection Reason: ");
                    String reason=sc.nextLine();

                    boolean result=expClaimController.approveOrRejectClaim(claimId,"Rejected",reason);

                    if(result){
                        System.out.println("Claim Rejected.");
                    }else{
                        System.out.println("Claim Rejection Failed.");
                    }

                }else if(ch==5){

                    showReimbursementMenu(sc,reimbursementController,finController,loggedInUser);

                }else if(ch==6){
                    System.out.println("Finance Logout Successful.");
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Finance Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showReimbursementMenu(Scanner sc,ReimbursementController reimbursementController,
                                             FinanceExecutiveController finController,User loggedInUser){

        while(true){
            System.out.println("\n--- REIMBURSEMENT MENU ---");
            System.out.println("1. Issue Reimbursement");
            System.out.println("2. View Reimbursement By Claim ID");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            try{
                int ch=sc.nextInt();
                sc.nextLine();

                if(ch==1){

                    FinanceExecutive finance=finController.getFinanceExecutiveById(loggedInUser.getUserId());

                    if(finance==null){
                        System.out.println("Finance profile not found.");
                        continue;
                    }

                    System.out.print("Enter Approved Claim ID: ");
                    int claimId=sc.nextInt();
                    System.out.print("Enter Reimbursement Amount: ");
                    double amount=sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Payment Mode: ");
                    String paymentMode=sc.nextLine();
                    System.out.print("Enter Transaction Reference: ");
                    String transactionRef=sc.nextLine();

                    Reimbursement reimbursement=new Reimbursement();
                    reimbursement.setClaimId(claimId);
                    reimbursement.setReimbursedAmount(amount);
                    reimbursement.setPaymentMode(paymentMode);
                    reimbursement.setTransactionRef(transactionRef);
                    reimbursement.setProcessedBy(finance.getEmployeeId());
                    reimbursement.setStatus("PROCESSED");

                    boolean result=reimbursementController.payClaim(reimbursement);

                    if(result){
                        System.out.println("Reimbursement Issued Successfully!");
                    }else{
                        System.out.println("Reimbursement Failed.");
                    }

                }else if(ch==2){

                    System.out.print("Enter Claim ID: ");
                    int claimId=sc.nextInt();
                    sc.nextLine();

                    Reimbursement reimbursement=reimbursementController.checkMyPayment(claimId);

                    if(reimbursement!=null){
                        System.out.println("Reimbursement ID: "+reimbursement.getReimbursementId());
                        System.out.println("Claim ID: "+reimbursement.getClaimId());
                        System.out.println("Amount: "+reimbursement.getReimbursedAmount());
                        System.out.println("Payment Mode: "+reimbursement.getPaymentMode());
                        System.out.println("Transaction Ref: "+reimbursement.getTransactionRef());
                        System.out.println("Status: "+reimbursement.getStatus());
                    }else{
                        System.out.println("Reimbursement not found.");
                    }

                }else if(ch==3){
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Reimbursement Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    public static void showDepartmentMenu(Scanner sc,DepartmentController depController){

        while(true){
            System.out.println("\n--- DEPARTMENT MENU ---");
            System.out.println("1. Add Department");
            System.out.println("2. View All Departments");
            System.out.println("3. View Department By Id");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            try{
                int ch=sc.nextInt();
                sc.nextLine();

                if(ch==1){

                    System.out.print("Enter Department Name: ");
                    String name=sc.nextLine();

                    System.out.print("Enter Manager ID: ");
                    int managerId=sc.nextInt();
                    sc.nextLine();

                    Department dept=new Department();
                    dept.setDepartmentName(name);
                    dept.setManagerId(managerId);

                    Department result=depController.addDepartment(dept);

                    if(result!=null){
                        System.out.println("Department Inserted Successfully!");
                    }else{
                        System.out.println("Failed to Insert Department");
                    }

                }else if(ch==2){

                    depController.getAllDepartments().forEach(d->
                            System.out.println(d.getDepartmentId()+" | "+d.getDepartmentName()+" | "+d.getManagerId()));

                }else if(ch==3){

                    System.out.print("Enter Department ID: ");
                    int id=sc.nextInt();
                    sc.nextLine();

                    Department d=depController.getDepartmentById(id);

                    if(d!=null){
                        System.out.println(d.getDepartmentId()+" | "+d.getDepartmentName()+" | "+d.getManagerId());
                    }else{
                        System.out.println("Department not found.");
                    }

                }else if(ch==4){
                    break;

                }else{
                    System.out.println("Invalid choice.");
                }

            }catch(Exception e){
                logger.log(Level.SEVERE,"Error in Department Menu",e);
                System.out.println("Something went wrong.");
                sc.nextLine();
            }
        }
    }

    }


