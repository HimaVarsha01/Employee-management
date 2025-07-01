package com.Employee.EmployeeMng.Repository;

import com.Employee.EmployeeMng.Model.EmployeeDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepo extends MongoRepository<EmployeeDetails, Integer> {

    Optional<EmployeeDetails> findByEmpnameAndEmpdesignationAndSalary(
            String empname, String empdesignation, Double salary);


    Optional<EmployeeDetails> findById(EmployeeDetails employee);
}
