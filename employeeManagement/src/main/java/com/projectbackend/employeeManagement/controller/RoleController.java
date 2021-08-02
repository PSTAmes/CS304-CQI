package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Role;
import com.projectbackend.employeeManagement.repository.RoleRepository;
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
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    //get All Roles
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    //create Role
    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role){
        return roleRepository.save(role);
    }


    //get role by ID
    @GetMapping("/roles/{roleID}")
    public ResponseEntity<Role> getRoleByID(@PathVariable("roleID") Long id){
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Role not exist with id : " + id));
        return ResponseEntity.ok(role);
    }


    //update Role
    @PutMapping("/roles/{roleID}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleID")  Long id, @NotNull @Validated @RequestBody Role roleDetail){
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Role does not exist with id : "+ id));

        role.setRoleID(roleDetail.getRoleID());
        role.setEmployees(roleDetail.getEmployees());
        role.setName(roleDetail.getName());


        Role updated = roleRepository.save(role);
        return ResponseEntity.ok(updated);
    }

    //delete Role
    @DeleteMapping("/roles/{roleID}")
    public Map<String, Boolean> deleteRole(@PathVariable("roleID") Long id)throws ResourceNotFoundException{
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Role does not exist with id : " + id));
        roleRepository.delete(role);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
