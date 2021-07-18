package com.projectbackend.employeeManagement.service;

import com.projectbackend.employeeManagement.dto.EmployeeResponse;
import com.projectbackend.employeeManagement.dto.ProjectResponse;

import java.util.List;
import java.util.Optional;

public interface IEmployee {

    List<EmployeeResponse> getAllEmployees();
    Optional<EmployeeResponse> findByID(Long id);
    Optional<EmployeeResponse> findByProject(String name);
    List<EmployeeResponse> findByDepartment(String name);
    EmployeeResponse assignToProject(Long id, ProjectResponse projectResponse);

}
