package com.crud.crud.controller;

import com.crud.crud.entity.EmployeeEntity;
import com.crud.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return new ResponseEntity<EmployeeEntity>(employeeService.saveEmployee(employee) , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updateEmployee", method = POST)
    public ResponseEntity<EmployeeEntity> updateEmployee(@RequestBody EmployeeEntity employee) {
        return new ResponseEntity<EmployeeEntity>(employeeService.updateEmployee(employee) , HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/deleteEmployee/{id}", method = DELETE)
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable("id") EmployeeEntity id) {
        return new ResponseEntity<EmployeeEntity>(employeeService.delete(id), HttpStatus.ACCEPTED);
    }
}
