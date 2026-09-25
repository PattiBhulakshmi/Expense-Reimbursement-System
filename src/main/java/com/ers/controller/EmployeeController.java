package com.ers.controller;

import com.ers.model.Employee;
import com.ers.service.EmployeeServiceImpl;
import com.ers.service.IEmployeeService;
import java.util.List;

public class EmployeeController {
    private IEmployeeService employeeService;

    public EmployeeController(){

        this.employeeService = new EmployeeServiceImpl();
    }

    public Employee addNewEmployee(Employee emp){

        return employeeService.addEmployee(emp);
    }

    public Employee getEmployeeById(int employee_id) {
        return employeeService.getEmployeeById(employee_id);
    }
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    public Employee getEmployeeByUserId(int userId) {

        return employeeService.getEmployeeByUserId(userId);
    }

}
