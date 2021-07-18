package com.projectbackend.employeeManagement.repository;


import com.projectbackend.employeeManagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

   /* @Query(value = "SELECT p  FROM Project p WHERE p.name = :name")
    Project findByName(@Param("name") String name);*/
}
