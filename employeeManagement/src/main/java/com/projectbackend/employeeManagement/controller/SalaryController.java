package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Salary;
import com.projectbackend.employeeManagement.repository.SalaryRepository;
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
public class SalaryController {

    @Autowired
    private SalaryRepository salaryRepository;

    //get All Salaries
    @GetMapping("/salary")
    public List<Salary> getAllSalary(){
        return salaryRepository.findAll();
    }

    //create salary
    @PostMapping("/salary")
    public Salary createSalary(@RequestBody Salary salary){
        return salaryRepository.save(salary);
    }


    //get salary by ID
    @GetMapping("/salary/{salaryID}")
    public ResponseEntity<Salary> getSalaryByID(@PathVariable("salaryID") Long id){
        Salary salary = salaryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Salary not exist with id : " + id));
        return ResponseEntity.ok(salary);
    }


    //update leaves
    @PutMapping("/salary/{salaryID}")
    public ResponseEntity<Salary> updateSalary(@PathVariable("salaryID")  Long id, @NotNull @Validated @RequestBody Salary salaryDetail){
        Salary salary = salaryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Salary does not exist with id : "+ id));

        salary.setSalaryID(salaryDetail.getSalaryID());
        salary.setAllowances(salaryDetail.getAllowances());
        salary.setArrears(salaryDetail.getArrears());
        salary.setBasic(salaryDetail.getBasic());
        salary.setEmployee(salaryDetail.getEmployee());
        salary.setEpf(salaryDetail.getEpf());
        salary.setMedical(salaryDetail.getMedical());
        salary.setMonth(salaryDetail.getMonth());
        salary.setOt(salaryDetail.getOt());

        Salary updated = salaryRepository.save(salary);
        return ResponseEntity.ok(updated);
    }

    //delete leaves
    @DeleteMapping("/salary/{salaryID}")
    public Map<String, Boolean> deleteSalary(@PathVariable("salaryID") Long id)throws ResourceNotFoundException{
        Salary salary = salaryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Salary does not exist with id : " + id));
        salaryRepository.delete(salary);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
