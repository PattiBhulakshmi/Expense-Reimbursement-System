package com.ers.dao;

import com.ers.model.Department;
import com.ers.model.Employee;

import java.util.List;

public interface IDepartmentDao {


    Department getDepartmentById(int departmentId);
    List<Department> getAllDepartments();

}
