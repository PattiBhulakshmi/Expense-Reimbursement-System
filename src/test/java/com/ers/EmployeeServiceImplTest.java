package com.ers;

import com.ers.dao.IEmployeeDao;
import com.ers.model.Employee;
import com.ers.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceImplTest {
    private IEmployeeDao employeeDao;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeDao = mock(IEmployeeDao.class);
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Test
    void testAddEmployee() {

        Employee employee = new Employee();

        when(employeeDao.addEmployee(employee)).thenReturn(employee);

        Employee result = employeeService.addEmployee(employee);

        assertNotNull(result);
        assertEquals(employee, result);

        verify(employeeDao).addEmployee(employee);
    }

    @Test
    void testGetEmployeeById() {

        int employeeId = 1;

        Employee employee = new Employee();

        when(employeeDao.getEmployeeById(employeeId)).thenReturn(employee);
        Employee result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(employee, result);

        verify(employeeDao).getEmployeeById(employeeId);
    }

    @Test
    void testGetAllEmployees() {

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeDao.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(employees, result);

        verify(employeeDao).getAllEmployees();
    }

    @Test
    void testGetEmployeeByUserId() {

        int userId = 1;

        Employee employee = new Employee();

        when(employeeDao.getEmployeeById(userId)).thenReturn(employee);

        Employee result = employeeService.getEmployeeByUserId(userId);

        assertNotNull(result);
        assertEquals(employee, result);

        verify(employeeDao).getEmployeeById(userId);
    }

}
