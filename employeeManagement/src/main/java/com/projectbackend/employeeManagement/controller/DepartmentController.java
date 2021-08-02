package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Department;
import com.projectbackend.employeeManagement.repository.DepartmentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    //get All departments
    @GetMapping("/departments")
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    //create department
    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department){
        return departmentRepository.save(department);
    }


    //get department by ID
    @GetMapping("/departments/{departmentID}")
    public ResponseEntity<Department> getDepartmentByID(@PathVariable Long id){
        Department department = departmentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Department not exist with id : " + id));
        return ResponseEntity.ok(department);
    }


    //update department
    @PutMapping("/departments/{departmentID}")
    public ResponseEntity<Department> updateDepartment(@PathVariable  Long id, @NotNull @Validated @RequestBody Department departmentDetail){
        Department department = departmentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id : "+ id));
        department.setDepartmentID(departmentDetail.getDepartmentID());
        department.setName(departmentDetail.getName());
        department.setEmployees(departmentDetail.getEmployees());
        department.setProjects(departmentDetail.getProjects());

        Department updated = departmentRepository.save(department);
        return ResponseEntity.ok(updated);
    }

    //delete department
    @DeleteMapping("/departments/{departmentID}")
    public Map<String, Boolean> deleteDepartment(@PathVariable("departmentID") Long id)throws ResourceNotFoundException{
        Department department = departmentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id : " + id));
        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
