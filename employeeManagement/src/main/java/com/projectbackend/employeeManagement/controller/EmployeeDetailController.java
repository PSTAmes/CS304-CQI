package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Employee;
import com.projectbackend.employeeManagement.model.EmployeeDetails;
import com.projectbackend.employeeManagement.model.Project;
import com.projectbackend.employeeManagement.repository.EmployeeDetailsRepository;
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
public class EmployeeDetailController {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    //getAll employeeDetails
    @GetMapping("/employeeDetails")
    public List<EmployeeDetails> getAllEmployeeDetails(){
        return employeeDetailsRepository.findAll();
    }


    //create employeeDetails
    @PostMapping("/employeeDetails")
    public EmployeeDetails createEmployeeDetails(@Validated @RequestBody EmployeeDetails employeeDetails){
        return employeeDetailsRepository.save(employeeDetails);
    }

    //get employeeDetails by ID
    @GetMapping("/employeeDetails/{detailID}")
    public ResponseEntity<EmployeeDetails> getDetailsByID(@PathVariable("detailID") Long detailID){
        EmployeeDetails employeeDetails = employeeDetailsRepository.findById(detailID).
                orElseThrow(() -> new ResourceNotFoundException("Employee Details not exist with id : " + detailID));
        return ResponseEntity.ok(employeeDetails);
    }

    //update employeeDetails
    @PutMapping("/employeeDetails/{detailID}")
    public ResponseEntity<EmployeeDetails> updateEmployeeDetail(@PathVariable("detailID") Long detailID, @NotNull @Validated @RequestBody EmployeeDetails employee) throws ResourceNotFoundException {
        EmployeeDetails details = employeeDetailsRepository.findById(detailID).
                orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + detailID));
        details.setDetailID(employee.getDetailID());
        details.setEmployee(employee.getEmployee());
        details.setIsActive(employee.getIsActive());
        details.setAddress(employee.getAddress());
        details.setDob(employee.getDob());
        details.setEmail(employee.getEmail());
        details.setFirstName(employee.getFirstName());
        details.setLastName(employee.getLastName());
        details.setGender(employee.getGender());
        details.setMobile(employee.getMobile());
        details.setJoinedDate(employee.getJoinedDate());

        EmployeeDetails updated = employeeDetailsRepository.save(details);
        return ResponseEntity.ok(updated);
    }

    //delete employeeDetails
    @DeleteMapping("/employeeDetails/{id}")
    public Map<String, Boolean> deleteEmployeeDetail(@PathVariable("id") Long id)throws ResourceNotFoundException{
        EmployeeDetails employeeDetails = employeeDetailsRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee Details not exist with id : " + id));
        employeeDetailsRepository.delete(employeeDetails);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
