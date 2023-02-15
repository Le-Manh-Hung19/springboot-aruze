package com.example.practicesb.controllers;

import com.example.practicesb.exception.ResourceNotFoundException;
import com.example.practicesb.models.Employee;
import com.example.practicesb.models.Product;
import com.example.practicesb.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("")
    List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NotFound"));
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee updatedEmp){
        Employee findEmp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NotFound"));
        findEmp.setFirstName(updatedEmp.getFirstName());
        findEmp.setLastName(updatedEmp.getLastName());
        findEmp.setEmailId(updatedEmp.getEmailId());
        employeeRepository.save(findEmp);
        return ResponseEntity.ok(findEmp);
    }
}
