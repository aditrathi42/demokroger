package com.example.demokroger.demokroger.controller;

import com.example.demokroger.demokroger.error.EmployeeNotFoundException;
import com.example.demokroger.demokroger.model.Employee;
import com.example.demokroger.demokroger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    @GetMapping("/")
    public String getEmployeeById() throws EmployeeNotFoundException, IOException, ExecutionException, InterruptedException {
         employeeService.getEmployeeById();
         return "Message published";

    }

}
