package com.nusan.nusanapi.validate;

import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeesValidate implements Validator {

    @Autowired
    private EmployeesService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","employees.name", "Name must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"lastName","employees.lastName", "Last name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"dni","employees.dni", "DNI must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNumber","employees.phoneNumber", "Phone number must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","employees.password", "Password must not be empty.");

        Employees employees = (Employees) target;

        String number = employees.getPhoneNumber().toString();
        if(!number.matches("^\\d{9}$")){
            errors.rejectValue("phoneNumber","employees.phoneNumber","Phone number  must have 9 digits.");
        }

        if (!employees.getEmail().matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")){
            errors.rejectValue("email","employees.email","Email not valid.");
        }

        if(!employees.getDni().matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$")){
            errors.rejectValue("dni","employees.dni","DNI is not valid.");
        }

        if(!employees.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,15}")){
            errors.rejectValue("password","employees.password","The password must have a capital letter, a digit, a special character and a size of 6 to 15 characters.");
        }

        if(!employees.getRol().equals("admin")){
            if(!employees.getRol().equals("employee")){
                errors.rejectValue("rol","employees.rol","The status can only be admin or employee");
            }
        }

        if(!service.getEmployeeByEmail(employees.getEmail())) {
            errors.reject("employees.email.repeated","This email already exists.");
        }
    }
}
