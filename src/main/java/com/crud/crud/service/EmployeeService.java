package com.crud.crud.service;

import com.crud.crud.entity.EmployeeEntity;
import com.crud.crud.repository.EmployeeRepository;
import com.crud.crud.sequenceIdGenerator.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SequenceGeneratorService sequenceGenerator;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SequenceGeneratorService sequenceGenerator) {
        this.employeeRepository = employeeRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public EmployeeEntity saveEmployee(EmployeeEntity employee) {

        try {
            String email = employee.getEmployeeEmailId();
            if (employeeRepository.findByEmployeeEmailId(email) != null) {
                throw new IllegalArgumentException("Employee with the same email already exists.");
            }
            employee.setId(sequenceGenerator.generateSequence(EmployeeEntity.SEQUENCE_NAME));
            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity existingEmployee = optionalEmployee.get();
            existingEmployee.setEmployeeFirstName(employee.getEmployeeFirstName());
            existingEmployee.setEmployeeLastName(employee.getEmployeeLastName());
            existingEmployee.setEmployeeEmailId(employee.getEmployeeEmailId());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found with ID: " + id);
        }
    }

    public List<EmployeeEntity> getEmployeelist() {
        return employeeRepository.findAll();
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
}