package com.nusan.nusanapi.controller;

import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.service.EmployeesService;
import com.nusan.nusanapi.service.entities.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private EmployeesService employeesService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<Login> loginForm(@RequestBody Login login){

        String email = login.getEmail();
        String password = login.getPassword();
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

        if(!employeesService.existEmployeeByEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            Employees employee =  employeesService.findByEmail(email);
            if(!encode.matches(password, employee.getPassword())){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(login);
            }
        }
    }
}
