package com.Employee.EmployeeMng.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employeedetails")
public class EmployeeDetails {

    @Id
    private Integer id;
    @NotBlank(message = "Employee name should not be Blank")
    private String empname;
    @NotBlank(message = "Designation should not be Blank")
    private String empdesignation;
    @Min(value = 10000 , message = "Salary must not be less than 10000")
    private Double salary;

    //Getters and Setters

}
