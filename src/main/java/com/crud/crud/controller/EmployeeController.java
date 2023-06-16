package com.crud.crud.controller;

import com.crud.crud.entity.EmployeeEntity;
import com.crud.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "http://localhost:4200")
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
        return new ResponseEntity<EmployeeEntity>(employeeService.saveEmployee(employee) , HttpStatus.ACCEPTED);
    }

}