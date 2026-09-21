package com.ers.controller;

import com.ers.model.Department;
import com.ers.model.Employee;
import com.ers.service.IDepartmentService;

import java.util.List;

public class DepartmentController {
    private IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Department addDepartment(Department department) {
        return null;
    }

    public boolean updateDepartment(Department department) {
        return false;
    }

    public Department getDepartmentById(int departmentId) {
        return null;
    }

    public List<Department> getAllDepartments() {
        return null;
    }

    public boolean deleteDepartmentById(int departmentId) {
        return false;
    }

    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return null;
    }

    public Department getDepartmentByManagerId(int managerId) {
        return null;
    }
}
