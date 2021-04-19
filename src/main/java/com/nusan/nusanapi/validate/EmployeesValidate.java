package com.nusan.nusanapi.validate;

import com.nusan.nusanapi.model.Employees;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeesValidate implements Validator {
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

        if (!employees.getEmail().matches("^(.+\\@.+\\..+)$")){
            errors.rejectValue("email","employees.email","Email not valid.");
        }

        if(!employees.getDni().matches("^\\d{8}(?:[-\\s]\\d{4})?$")){
            errors.rejectValue("dni","employees.dni","DNI is not valid.");
        }

        if(!employees.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,15}")){
            errors.rejectValue("password","employees.password","The password must have a capital letter, a digit, a special character and a size of 6 to 15 characters.");
        }

        if(!employees.getStatus().equals("conectado")||!employees.getStatus().equals("desconectado")){
            errors.rejectValue("status","employees.status","The status can only be connected or disconnected");
        }
    }
}
