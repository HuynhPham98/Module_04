package com.ra.demo.service.employee;

import com.ra.demo.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    boolean create(Employee employee);
    Employee findById(Long id);
    boolean update(Employee employee, Long id);
    void delete(Long id);
    List<Employee> findEmployeeByName(String name);
}
