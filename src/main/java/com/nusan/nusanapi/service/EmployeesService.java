package com.nusan.nusanapi.service;

import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository repository;

    public Employees create(Employees employees){return repository.save(employees);}

    public Employees getEmployeeById(Long id){return repository.findById(id).orElse(null);}

    public Boolean getEmployeeByDni(String dni){return repository.existsByDni(dni);}

    public Boolean existsById(Long id){return repository.existsById(id);}

    public void deleteEmployeeById(Long id){repository.deleteById(id);}

    public void deleteEmployee(Employees employee){repository.delete(employee);}
}
