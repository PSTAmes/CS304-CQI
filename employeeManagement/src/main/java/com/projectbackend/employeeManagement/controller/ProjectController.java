package com.projectbackend.employeeManagement.controller;

import com.projectbackend.employeeManagement.exception.ResourceNotFoundException;
import com.projectbackend.employeeManagement.model.Project;
import com.projectbackend.employeeManagement.repository.DepartmentRepository;
import com.projectbackend.employeeManagement.repository.ProjectRepository;
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
public class ProjectController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProjectRepository projectRepository;

    //get All projects
    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }


    //create project
    @PostMapping("/projects")
    public Project createProject(@Validated @RequestBody Project project){
        return projectRepository.save(project);
    }

    //get project by ID
    @GetMapping("/projects/{projectid}")
    public ResponseEntity<Project> getProjectByID(@PathVariable("projectid") Long id){
       Project project = projectRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Project does not exist with id : " + id));
        return ResponseEntity.ok(project);
    }

    //update project
    @PutMapping("/projects/{projectid}")
    public ResponseEntity<Project> updateProject(@PathVariable("projectid") Long id, @NotNull @Validated @RequestBody Project projectDetail) throws ResourceNotFoundException {
        Project project = projectRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Project not exist with id : " + id));
        project.setDepartment(projectDetail.getDepartment());
        project.setDueDate(projectDetail.getDueDate());
        project.setName(projectDetail.getName());
        project.setStartDate(projectDetail.getStartDate());
        project.setIsActive(projectDetail.getIsActive());
        project.setEmployees(projectDetail.getEmployees());

        Project updated = projectRepository.save(project);
        return ResponseEntity.ok(updated);
    }

    //delete project
    @DeleteMapping("/projects/{projectsid}")
    public Map<String, Boolean> deleteProject(@PathVariable("projectid") Long id)throws ResourceNotFoundException{
        Project project = projectRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Project not exist with id : " + id));
        projectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
