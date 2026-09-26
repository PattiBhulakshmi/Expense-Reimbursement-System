package com.ers.service;

import com.ers.dao.EmployeeDaoImpl;
import com.ers.dao.IEmployeeDao;
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


    @Override
    public Employee getEmployeeById(int employeeId) {

        return employeeDao.getEmployeeById(employeeId);
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
