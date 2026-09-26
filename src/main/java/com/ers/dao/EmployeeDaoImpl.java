package com.ers.dao;

import com.ers.model.Employee;
import com.ers.model.User;
import com.ers.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {
    JDBCUtil jdbcUtil;

    public EmployeeDaoImpl(){

        this.jdbcUtil=new JDBCUtil();
    }

    @Override
    public Employee addEmployee(Employee employee)
    {
        String sql="INSERT INTO employee(user_id,full_name, email, department_id) VALUES (?, ?, ?, ?)";

        try(Connection con = jdbcUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setInt(1, employee.getUserId());
            ps.setString(2, employee.getFullName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, 1);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee getEmployeeById(int userId) {
        String sql =  "SELECT employee_id, user_id,full_name, email, department_id FROM employee WHERE user_id = ?";
        Employee employee = null;

        try (Connection con = jdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                employee= new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setUserId(rs.getInt("user_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setDepartmentId(rs.getInt("department_id"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT  employee_id,user_id,full_name,email,department_id FROM employee";
        try (Connection con = jdbcUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()){
                User user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setRole(rs.getString("role"));
                Employee emp = new Employee(
                        rs.getString("full_name"),
                        user,
                        rs.getString("email"),
                        rs.getInt("department_id")
                );
                emp.setEmployeeId(rs.getInt("employee_id"));
                list.add(emp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
