package com.Employee.EmployeeMng.Service;

import com.Employee.EmployeeMng.Model.EmployeeDetails;
import com.Employee.EmployeeMng.Repository.EmployeeRepo;
import com.Employee.EmployeeMng.exceptions.ResourceAlreadyExistException;
import com.Employee.EmployeeMng.exceptions.ResourceNotFoundException;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    // Getservice
    public Optional<EmployeeDetails> getEmployeeId(Integer id){
        // Employee not found
        if(!employeeRepo.existsById(id))
        {
            throw new ResourceNotFoundException("Employee not found with Id: " +id);
        }
        return employeeRepo.findById(id);
    }

    // PostService
    public EmployeeDetails saveEmployee(EmployeeDetails employee) {
        // Find the Existing employee by ID
         if(employeeRepo.existsById(employee.getId())){
             throw new ResourceAlreadyExistException("Employee Already exists with Id:" + employee.getId());
         }

        return employeeRepo.save(employee);
    }

    // PutService
    public EmployeeDetails updateEmployee(Integer id, EmployeeDetails newdetails) {

        // 1. Find the existing employee by ID
        EmployeeDetails existingEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        // 2. Check someone else has same details
        Optional<EmployeeDetails> duplicate = employeeRepo
                .findByEmpnameAndEmpdesignationAndSalary(newdetails.getEmpname(), newdetails.getEmpdesignation(), newdetails.getSalary());
        // 3. Checking for duplicates
        if(duplicate.isPresent() && !duplicate.get().equals(id))
        {
            throw new ResourceAlreadyExistException("Employee Already exists with ID: "+ duplicate.get().getId());
        }
        // 4. Update fields (but NOT the ID!)
        existingEmployee.setEmpname(newdetails.getEmpname());
        existingEmployee.setEmpdesignation(newdetails.getEmpdesignation());
        existingEmployee.setSalary(newdetails.getSalary());

        return employeeRepo.save(existingEmployee);
      }

      // DeleteService
       public EmployeeDetails deleteEmployee(Integer id) {
        Optional<EmployeeDetails> employeedel = employeeRepo.findById(id);
        if(employeedel.isPresent()) {
            employeeRepo.deleteById(id);
            return employeedel.get();
        }
        else
            return null;

     }

     // GetALLService
     public List<EmployeeDetails> getAllEmployees() {
        return employeeRepo.findAll();
    }


}
