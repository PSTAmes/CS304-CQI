package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Employee;
import com.projectbackend.employeeManagement.repository.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get All Employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //create employee restapi
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //get employee byID
    @GetMapping("/employees/{employeeID}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable("employeeID") Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
        return ResponseEntity.ok(employee);
    }

    //update employee rest api
    @PutMapping("/employees/{employeeID}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeID") Long id, @NotNull @RequestBody Employee employeeDetail) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id : " + id));
        employee.setEmployeeID(employeeDetail.getEmployeeID());
        employee.setDepartment(employeeDetail.getDepartment());
        employee.setSalaries(employeeDetail.getSalaries());
        employee.setLeaves(employeeDetail.getLeaves());
        employee.setProject(employeeDetail.getProject());
        employee.setEmployeeDetails(employeeDetail.getEmployeeDetails());
        employee.setRole(employeeDetail.getRole());

        Employee updated = employeeRepository.save(employee);
        return ResponseEntity.ok(updated);
    }

    //delete employee
    @DeleteMapping("/employees/{employeeID}")
    public Map<String, Boolean> deleteEmployee(@PathVariable("employeeID") Long id)throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id : " + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}