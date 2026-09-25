package com.ers.service;

import com.ers.dao.DepartmentDaoImpl;
import com.ers.dao.IDepartmentDao;
import com.ers.model.Department;
import com.ers.model.Employee;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService{
    IDepartmentDao departmentDao;

   public  DepartmentServiceImpl(){
       this.departmentDao=new DepartmentDaoImpl();
   }

    @Override
    public int insertDepartment(Department dept) {

        return departmentDao.insertDepartment(dept);
    }

    @Override
    public Department getDepartmentById(int departmentId)
    {

        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public List<Department> getAllDepartments()
    {
        return departmentDao.getAllDepartments();
    }


    }

