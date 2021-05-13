package com.nusan.nusanapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.service.EmployeesService;
import com.nusan.nusanapi.validate.EmployeesValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeesService service;

    @Autowired
    EmployeesValidate validate;


    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees, BindingResult result){
        validate.validate(employees, result);
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if(!service.existEmployeeByDni(employees.getDni())){
            Employees employeesCreated = service.create(employees);
            return new ResponseEntity<>(employeesCreated, HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id){
        Employees employees = service.getEmployeeById(id);
        if(employees != null){
            return  ResponseEntity.ok(service.getEmployeeById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(path = "/employeesDni/{dni}", method = RequestMethod.GET)
    public ResponseEntity<Employees> getEmployeeByDni(@PathVariable String dni){
        String dniEmp = dni.replace(" ","");
        Employees employees = service.getEmployeeByDni(dniEmp);
        if(employees != null){
            return ResponseEntity.ok(employees);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employees> deleteEmployeeById(@PathVariable Long id){
        if(!service.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            service.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/employeesDni/{dni}", method = RequestMethod.DELETE)
    public ResponseEntity<Employees> deleteEmployeeById(@PathVariable String dni){
        if(!service.existEmployeeByDni(dni)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Employees employees = service.getEmployeeByDni(dni);
            service.deleteEmployeeById(employees.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping(path = "/employees/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable long id, @RequestBody JsonPatch patch){
        if(!service.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try{
            Employees employees = service.getEmployeeById(id);
            Employees employeePatch = service.applyPatchToUser(patch, employees);
            service.create(employeePatch);
            return ResponseEntity.ok(employeePatch);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
