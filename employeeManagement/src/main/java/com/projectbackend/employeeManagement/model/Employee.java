package com.projectbackend.employeeManagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    @OneToOne @JoinColumn(name = "detailID")
    private EmployeeDetails employeeDetails;

    @ManyToOne @JoinColumn(name = "projectID")
    private Project project;

    @ManyToOne @JoinColumn(name = "roleID")
    private Role role;

    @ManyToOne @JoinColumn(name = "departmentID")
    private Department department;

    @OneToMany(targetEntity = Salary.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "es_fk", referencedColumnName = "employeeID")
    private Set<Salary> salaries = new HashSet<>();

    @OneToMany(targetEntity = Leave.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "le_fk", referencedColumnName = "employeeID")
    private Set<Leave> leaves = new HashSet<>();

}
