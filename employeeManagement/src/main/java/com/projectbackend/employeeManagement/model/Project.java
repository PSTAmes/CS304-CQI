package com.projectbackend.employeeManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectID;

    @Column(name = "name")
    private String name;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "isActive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;

    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pe_fk", referencedColumnName = "projectID")
    private Set<Employee> employees = new HashSet<>();
}


