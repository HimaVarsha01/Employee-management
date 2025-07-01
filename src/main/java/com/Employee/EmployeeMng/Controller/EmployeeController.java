package com.Employee.EmployeeMng.Controller;

import com.Employee.EmployeeMng.Model.EmployeeDetails;
import com.Employee.EmployeeMng.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Optional<EmployeeDetails> getEmployeeId(@PathVariable Integer id) {
       return employeeService.getEmployeeId(id);
    }

    @PostMapping("/")
    public EmployeeDetails saveEmployee(@Valid @RequestBody EmployeeDetails employee){
       return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Optional<EmployeeDetails> updateEmployee(@Valid @PathVariable Integer id, @RequestBody EmployeeDetails updatedemployee){
        return Optional.ofNullable(employeeService.updateEmployee(id, updatedemployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDetails> deleteEmployee(@PathVariable Integer id){
        EmployeeDetails employeedel = employeeService.deleteEmployee(id);
        if(employeedel==null){
            return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.ok(employeedel);
    }

    @GetMapping("/")
    public List<EmployeeDetails> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

}
