package com.projectbackend.employeeManagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveID;

    @ManyToOne @JoinColumn(name = "employeeID")
    private Employee employee;

    @Column(name = "reason")
    private String reason;

    @Column(name = "fromDate")
    private String fromDate;

    @Column(name = "toDate")
    private String toDate;

    @Column(name = "type")
    private String leaveType;

    @Column(name = "annual")
    private int availableAnnual;

    @Column(name = "sick")
    private int availableSick;

}
