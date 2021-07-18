package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Leave;
import com.projectbackend.employeeManagement.repository.LeaveRepository;
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
public class LeaveController {

    @Autowired
    private LeaveRepository leaveRepository;

    //get All leaves
    @GetMapping("/leaves")
    public List<Leave> getAllLeaves(){
        return leaveRepository.findAll();
    }

    //create leaves
    @PostMapping("/leaves")
    public Leave createLeave(@RequestBody Leave leave){
        return leaveRepository.save(leave);
    }


    //get leaves by ID
    @GetMapping("/leaves/{leaveID}")
    public ResponseEntity<Leave> getLeaveByID(@PathVariable("leaveID") Long id){
        Leave leave = leaveRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Leave not exist with id : " + id));
        return ResponseEntity.ok(leave);
    }


    //update leaves
    @PutMapping("/leaves/{leaveID}")
    public ResponseEntity<Leave> updateLeave(@PathVariable("leaveID")  Long id, @NotNull @Validated @RequestBody Leave leaveDetail){
        Leave leave = leaveRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Leave does not exist with id : "+ id));
        leave.setLeaveID(leaveDetail.getLeaveID());
        leave.setLeaveType(leaveDetail.getLeaveType());
        leave.setAvailableAnnual(leaveDetail.getAvailableAnnual());
        leave.setEmployee(leaveDetail.getEmployee());
        leave.setReason(leaveDetail.getReason());
        leave.setAvailableSick(leaveDetail.getAvailableSick());
        leave.setFromDate(leaveDetail.getFromDate());
        leave.setToDate(leaveDetail.getToDate());

        Leave updated = leaveRepository.save(leave);
        return ResponseEntity.ok(updated);
    }

    //delete leaves
    @DeleteMapping("/leaves/{leaveID}")
    public Map<String, Boolean> deleteLeave(@PathVariable("leaveID") Long id)throws ResourceNotFoundException{
        Leave leave = leaveRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Leave does not exist with id : " + id));
        leaveRepository.delete(leave);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
