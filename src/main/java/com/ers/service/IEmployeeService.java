package com.ers.service;


import com.ers.model.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    Employee getEmployeeByUserId(int userId);

}
