package com.projectbackend.employeeManagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProjectResponse {

    private String projectName;
    private String firstname;
    private String lastName;
    private Long id;


}
