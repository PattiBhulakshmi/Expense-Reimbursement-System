package com.ers.dao;

import com.ers.model.Department;
import com.ers.model.Employee;
import com.ers.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao{
    JDBCUtil jdbcUtil;

    public DepartmentDaoImpl(){
        this.jdbcUtil=new JDBCUtil();
    }


    @Override
    public int insertDepartment(Department dept) {
        String sql = "INSERT INTO department(department_name) VALUES (?)";
        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, dept.getDepartmentName());
            return ps.executeUpdate();
        }catch(Exception e){
        e.printStackTrace(); }
        return 0;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM department WHERE department_id=?";
        try(Connection con =  jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Department d = new Department();
                d.setDepartmentId(rs.getInt("department_id"));
                d.setDepartmentName(rs.getString("department_name"));
                d.setManagerId(rs.getInt("manager_id"));
                return d;
            }
        }catch(Exception e){
            e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {

        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection con = jdbcUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Department d = new Department();
                d.setDepartmentId(rs.getInt("department_id"));
                d.setDepartmentName(rs.getString("department_name"));
                d.setManagerId(rs.getInt("manager_id"));
                list.add(d);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }





}
