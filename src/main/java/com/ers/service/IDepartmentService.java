package com.ers.service;

import com.ers.model.Department;
import com.ers.model.Employee;

import java.util.List;

public interface IDepartmentService {

    Department getDepartmentById(int departmentId);
    List<Department> getAllDepartments();

}
