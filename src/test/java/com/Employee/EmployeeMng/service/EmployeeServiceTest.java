package com.Employee.EmployeeMng.service;
import com.Employee.EmployeeMng.Model.EmployeeDetails;
import com.Employee.EmployeeMng.Repository.EmployeeRepo;
import com.Employee.EmployeeMng.Service.EmployeeService;
import com.Employee.EmployeeMng.exceptions.ResourceAlreadyExistException;
import com.Employee.EmployeeMng.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

        @Mock
        private EmployeeRepo employeeRepo;

        @InjectMocks
        private EmployeeService employeeService;

        @Test
        void testGetEmployeeId_Found() {
            EmployeeDetails emp = new EmployeeDetails(1, "Naveen", "Developer", 20000.0);
            when(employeeRepo.existsById(1)).thenReturn(true);
            when(employeeRepo.findById(1)).thenReturn(Optional.of(emp));

            Optional<EmployeeDetails> result = employeeService.getEmployeeId(1);

            assertTrue(result.isPresent());
            assertEquals("Naveen", result.get().getEmpname());
        }

        @Test
        void testGetEmployeeId_NotFound() {
            when(employeeRepo.existsById(100)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () -> {
                employeeService.getEmployeeId(100);
            });
        }

        @Test
        void testSaveEmployee_Success() {
            EmployeeDetails emp = new EmployeeDetails(3, "Ram", "Sr.Developer", 50000.0);
            when(employeeRepo.existsById(emp.getId())).thenReturn(false);
            when(employeeRepo.save(emp)).thenReturn(emp);

            EmployeeDetails saved = employeeService.saveEmployee(emp);

            assertEquals("Ram", saved.getEmpname());
        }

        @Test
        void testSaveEmployee_AlreadyExists() {
            EmployeeDetails emp = new EmployeeDetails(4, "Vani", "sr.Developer-1", 70000.0);
            when(employeeRepo.existsById(emp.getId())).thenReturn(true);

            assertThrows(ResourceAlreadyExistException.class, () -> {
                employeeService.saveEmployee(emp);
            });
        }

        @Test
        void testUpdateEmployee_Success() {
            EmployeeDetails existing = new EmployeeDetails(3, "Ram", "Sr.Developer", 50000.0);
            EmployeeDetails updated = new EmployeeDetails(3, "Ram Updated", "Lead", 70000.0);

            when(employeeRepo.findById(3)).thenReturn(Optional.of(existing));
            when(employeeRepo.findByEmpnameAndEmpdesignationAndSalary(
                    updated.getEmpname(), updated.getEmpdesignation(), updated.getSalary()
            )).thenReturn(Optional.empty());

            when(employeeRepo.save(existing)).thenReturn(existing);

            EmployeeDetails result = employeeService.updateEmployee(3, updated);

            assertEquals("Ram Updated", result.getEmpname());
            assertEquals("Lead", result.getEmpdesignation());
        }

        @Test
        void testUpdateEmployee_Duplicate() {
            EmployeeDetails existing = new EmployeeDetails(3, "Ram", "Sr.Developer", 50000.0);
            EmployeeDetails duplicate = new EmployeeDetails(5, "satya sai", "Sr.Tester-1", 40000.0);

            when(employeeRepo.findById(3)).thenReturn(Optional.of(existing));
            when(employeeRepo.findByEmpnameAndEmpdesignationAndSalary(
                    duplicate.getEmpname(), duplicate.getEmpdesignation(), duplicate.getSalary()
            )).thenReturn(Optional.of(duplicate));

            assertThrows(ResourceAlreadyExistException.class, () -> {
                employeeService.updateEmployee(3, duplicate);
            });
        }

        @Test
        void testDeleteEmployee_Found() {
            EmployeeDetails emp = new EmployeeDetails(6, "satya sai", "Sr.Tester-1", 80000.0);
            when(employeeRepo.findById(6)).thenReturn(Optional.of(emp));

            EmployeeDetails result = employeeService.deleteEmployee(6);

            verify(employeeRepo, times(1)).deleteById(6);
            assertEquals("satya sai", result.getEmpname());
        }

        @Test
        void testDeleteEmployee_NotFound() {
            when(employeeRepo.findById(6)).thenReturn(Optional.empty());

            EmployeeDetails result = employeeService.deleteEmployee(6);

            assertNull(result);
        }

    @Test
    void testGetAllEmployees() {
        List<EmployeeDetails> list = Arrays.asList(
                new EmployeeDetails(1, "Naveen", "Developer", 20000.0),
                new EmployeeDetails(3, "Ram", "sr.Developer", 50000.0),
                new EmployeeDetails(4, "vani", "sr.Developer-1", 70000.0),
                new EmployeeDetails(5, "satya sai", "sr.Tester-1", 40000.0),
                new EmployeeDetails(6, "satya sai", "sr.Tester-1", 80000.0),
                new EmployeeDetails(7, "Teja", "sr.Tester-2", 90000.0)
        );

        when(employeeRepo.findAll()).thenReturn(list);

        List<EmployeeDetails> result = employeeService.getAllEmployees();

        assertEquals(6, result.size());
        assertEquals("Naveen", result.get(0).getEmpname());
        assertEquals("Developer", result.get(0).getEmpdesignation());
        assertEquals(20000.0, result.get(0).getSalary());
    }

}

