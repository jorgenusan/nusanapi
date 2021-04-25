package com.nusan.nusanapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public Employees create(Employees employees){return repository.save(employees);}

    public Employees getEmployeeById(Long id){return repository.findById(id).orElse(null);}

    public Boolean getEmployeeByDni(String dni){return repository.existsByDni(dni);}

    public Boolean getEmployeeByEmail(String email){return repository.existsByEmail(email);}

    public Boolean existsById(Long id){return repository.existsById(id);}

    public void deleteEmployeeById(Long id){repository.deleteById(id);}

    public void deleteEmployee(Employees employee){repository.delete(employee);}

    public Employees applyPatchToUser(JsonPatch patch, Employees targetUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, Employees.class);
    }
}
