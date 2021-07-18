package com.projectbackend.employeeManagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryID;

    @ManyToOne @JoinColumn(name = "employeeID")
    private Employee employee;

    @Column(name = "basic")
    private Double basic;

    @Column(name = "epf")
    private Double epf;

    @Column(name = "allowances")
    private Double allowances;

    @Column(name = "ot")
    private Double ot;

    @Column(name = "arrears")
    private Double arrears;

    @Column(name = "medical")
    private Double medical;

    @Column(name = "month ")
    private String month;


}
