package com.ers.service;

import com.ers.dao.EmployeeDaoImpl;
import com.ers.dao.IEmployeeDao;
import com.ers.exception.EmployeeNotFoundException;
import com.ers.model.Employee;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService{
    IEmployeeDao employeeDao;


    // Constructor used for Mockito testing
    public EmployeeServiceImpl(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeServiceImpl()
    {
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public Employee addEmployee(Employee employee) {

        return employeeDao.addEmployee(employee);
    }

// custome exception
    @Override
    public Employee getEmployeeById(int employeeId) {

        Employee employee = employeeDao.getEmployeeById(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException(
                    "Employee not found with ID: " + employeeId
            );
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeByUserId(int userId) {

        return employeeDao.getEmployeeById(userId);
    }


}
