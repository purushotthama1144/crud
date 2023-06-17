package com.crud.crud.entity;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Transient
    public static final String SEQUENCE_NAME = "My_total_sequence_id's";

    @Id
    public long id;
    public  String employeeFirstName;
    public  String employeeLastName;
    public String employeeEmailId;
}
