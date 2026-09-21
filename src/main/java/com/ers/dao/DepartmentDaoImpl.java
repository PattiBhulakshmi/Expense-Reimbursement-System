package com.ers.dao;

import com.ers.model.Department;
import com.ers.model.Employee;

import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao{
    @Override
    public Department addDepartment(Department department) {
        return null;
    }

    @Override
    public boolean updateDepartment(Department department) {
        return false;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return List.of();
    }

    @Override
    public boolean deleteDepartmentById(int departmentId) {
        return false;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return List.of();
    }

    @Override
    public Department getDepartmentByManagerId(int managerId) {
        return null;
    }
}
