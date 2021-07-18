package com.projectbackend.employeeManagement.repository;

import com.projectbackend.employeeManagement.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
