package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees, Long> {
    Boolean existsByDni(String dni);
}
