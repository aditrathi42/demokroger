package com.example.demokroger.demokroger.service;

import com.example.demokroger.demokroger.Pub_sub.Publisher1;
import com.example.demokroger.demokroger.error.EmployeeNotFoundException;
import com.example.demokroger.demokroger.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class EmployeeService {
//    @Autowired
//    EmployeeRepository employeeRepository;
//
    @Autowired
    private Publisher1 publisher;

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

    public void getEmployeeById() throws EmployeeNotFoundException, IOException, ExecutionException, InterruptedException {
        publisher.publisherExample();
    }
}
