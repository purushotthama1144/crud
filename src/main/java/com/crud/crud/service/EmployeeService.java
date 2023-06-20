package com.crud.crud.service;

import com.crud.crud.entity.EmployeeEntity;
import com.crud.crud.repository.EmployeeRepository;
import com.crud.crud.sequenceIdGenerator.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public EmployeeEntity saveEmployee(EmployeeEntity entity) {
        try {
            entity.setId((int) sequenceGenerator.generateSequence(EmployeeEntity.SEQUENCE_NAME));
            return employeeRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeeEntity updateEmployee(EmployeeEntity entity) {
        return employeeRepository.save(entity);
    }

    public List<EmployeeEntity> getEmployeelist() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity delete(EmployeeEntity entity) {
        this.employeeRepository.deleteById(entity.id);
        return entity;
    }
}
