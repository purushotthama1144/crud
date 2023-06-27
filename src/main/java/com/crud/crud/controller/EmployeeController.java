package com.crud.crud.controller;

import com.crud.crud.entity.EmployeeEntity;
import com.crud.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employeeList", method = GET)
    public ResponseEntity<List<EmployeeEntity>> getEmployeelist() {
        return new ResponseEntity<List<EmployeeEntity>>(employeeService.getEmployeelist() , HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/addEmployee", method = POST)
    public ResponseEntity<EmployeeEntity> saveEmployee(@RequestBody EmployeeEntity employee) {
        try {
            return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/deleteEmployee/{id}", method = DELETE)
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/employeeDetails/{id}", method = GET)
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/updateEmployee/{id}" , method = PUT)
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee) {
        EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
