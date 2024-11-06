package com.ra.demo.service.employee;

import com.ra.demo.model.Employee;
import com.ra.demo.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean create(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Employee employee, Long id) {
        Employee employeeUpdate = employeeRepository.findById(id).orElse(null);
        if (employeeUpdate != null) {
            employeeUpdate.setFullName(employee.getFullName());
            employeeUpdate.setAddress(employee.getAddress());
            employeeUpdate.setEmail(employee.getEmail());
            employeeUpdate.setDepartmentName(employee.getDepartmentName());
            employeeUpdate.setDateOfBirth(employee.getDateOfBirth());
            employeeUpdate.setStatus(employee.isStatus());

            employeeRepository.save(employeeUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findAllByFullNameContains(name);
    }
}
