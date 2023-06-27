package com.crud.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crud.crud.entity.EmployeeEntity;

public interface EmployeeRepository extends MongoRepository<EmployeeEntity, Long> {
EmployeeEntity findByEmployeeEmailId(String email);
}
