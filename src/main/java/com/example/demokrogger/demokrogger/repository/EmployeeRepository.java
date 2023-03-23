package com.example.demokrogger.demokrogger.repository;

import com.example.demokrogger.demokrogger.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
