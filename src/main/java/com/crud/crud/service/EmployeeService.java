package com.crud.crud.service;

import com.crud.crud.entity.EmployeeEntity;
import com.crud.crud.repository.EmployeeRepository;
import com.crud.crud.sequenceIdGenerator.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        entity.setId(sequenceGenerator.generateSequence(EmployeeEntity.SEQUENCE_NAME));
        return employeeRepository.save(entity);
    }

    public List<EmployeeEntity> getEmployeelist() {
        return employeeRepository.findAll();
    }
}
