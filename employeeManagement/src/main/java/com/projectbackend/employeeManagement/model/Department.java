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
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentID;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Project.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "dp_fk", referencedColumnName = "departmentID")
    private Set<Project>  projects = new HashSet<>();

    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "de_fk", referencedColumnName = "departmentID")
    private Set<Employee> employees = new HashSet<>();


    public Department(Long departmentID, String name) {
        this.departmentID = departmentID;
        this.name = name;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
