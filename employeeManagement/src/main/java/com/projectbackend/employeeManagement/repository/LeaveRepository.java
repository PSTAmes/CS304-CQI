package com.projectbackend.employeeManagement.repository;

import com.projectbackend.employeeManagement.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
