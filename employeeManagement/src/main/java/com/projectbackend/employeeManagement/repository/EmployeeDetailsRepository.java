package com.projectbackend.employeeManagement.repository;

import com.projectbackend.employeeManagement.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
}
