package net.employee.management.springcrudproject.controller;



import io.swagger.v3.oas.annotations.Operation;
import net.employee.management.springcrudproject.entity.Employee;
import net.employee.management.springcrudproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// swagger url
//http://localhost:8088/swagger-ui/index.html#/


@RestController
@RequestMapping("/api/vi")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    @Operation(summary = "get all employees")
    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "get employee by id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    @Operation(summary = "creates an employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return repository.save(employee);
    }


//    update method with issues

    @PutMapping("/employees/{id}")
    @Operation(summary = "save an employee")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        return repository.findById(employeeId)
                .map(updateStudent -> {
                    updateStudent.setFirstName(employeeDetails.getFirstName());
                    updateStudent.setLastName(employeeDetails.getLastName());
                    updateStudent.setEmail(employeeDetails.getEmail());
                    return repository.save(updateStudent);
                })
                .orElseGet(() -> {
                    employeeDetails.setId(employeeId);
                    return repository.save(employeeDetails);
                });
    }


    @DeleteMapping("/employees/{id}")
    @Operation(summary = "deletes an employee")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        repository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
