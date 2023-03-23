package com.example.demokrogger.demokrogger.service;

import com.example.demokrogger.demokrogger.error.EmployeeNotFoundException;
import com.example.demokrogger.demokrogger.model.Employee;
import com.example.demokrogger.demokrogger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found!"));
    }
}
