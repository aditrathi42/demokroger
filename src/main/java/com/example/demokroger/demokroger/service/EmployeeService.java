package com.example.demokroger.demokroger.service;

import com.example.demokroger.demokroger.error.EmployeeNotFoundException;
import com.example.demokroger.demokroger.model.Employee;
import com.example.demokroger.demokroger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@Service
public class EmployeeService {
//    @Autowired
//    EmployeeRepository employeeRepository;
//
    public Employee addEmployee(Employee employee) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://us-central1-directed-linker-381606.cloudfunctions.net/csv ";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = employee.toString();
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return employee;
    }

//    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found!"));
//    }
}
