package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Employees;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees, Long> {
    Boolean existsByDni(String dni);
    Boolean existsByEmail(String email);
    Employees findByDni(String dni);

    @Query("select e from employees e where e.email = ?1")
    Employees findByEmail(String email);
}
