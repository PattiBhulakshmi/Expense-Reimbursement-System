package com.ers.controller;

import com.ers.model.Department;
import com.ers.model.Employee;
import com.ers.service.IDepartmentService;

import java.util.List;

public class DepartmentController {
    private IDepartmentService departmentService;



    public DepartmentController() {

        this.departmentService = departmentService;
    }

    public Department addDepartment(Department department) {

        int result = departmentService.insertDepartment(department);
        if(result > 0) return department;
        return null;
    }


    public Department getDepartmentById(int departmentId) {

        return departmentService.getDepartmentById(departmentId);
    }

    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }





}
