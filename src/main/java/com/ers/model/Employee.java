package com.ers.model;

public class Employee {

    private int employeeId;
    private User user;
    private String fullName;
    private String email;
    private int departmentId;

    public Employee() {

    }

    public Employee(String fullName, User user, String email, int departmentId) {
        this.fullName = fullName;
        this.user=user;
        this.email = email;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getUserId() {
        return user.getUserId();
    }

    public void setUserId(int userId) {
        this.user.setUserId(userId);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", user=" + user +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
