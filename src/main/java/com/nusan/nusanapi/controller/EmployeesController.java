package com.nusan.nusanapi.controller;


import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.service.EmployeesService;
import com.nusan.nusanapi.validate.EmployeesValidate;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeesService service;

    /*
    @Autowired
    private EmployeesValidate validate;
     */

    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees/*, BindingResult result*/){
        /*validate.validate(employees, result);
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }*/

        if(!service.getEmployeeByDni(employees.getDni())){
            Employees employeesCreated = service.create(employees);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id){
        return  ResponseEntity.ok(service.getEmployeeById(id));
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employees> deleteEmployeeById(@PathVariable Long id){
        if(!service.existsById(id)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            service.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/employees", method = RequestMethod.PUT)
    public ResponseEntity<Employees> modifyEmployee(@RequestBody Employees employees){
        if(!service.existsById(employees.getId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            service.deleteEmployee(employees);
            service.create(employees);
            return  ResponseEntity.ok(service.getEmployeeById(employees.getId()));
        }
    }
}
