package com.projectbackend.employeeManagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
@Entity
@Table(name = "employeeDetails")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detailID")
    private Long detailID;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "emp_det_fk", referencedColumnName = "employeeID")
    private Employee employee;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "joinDate")
    private String joinedDate;

}
