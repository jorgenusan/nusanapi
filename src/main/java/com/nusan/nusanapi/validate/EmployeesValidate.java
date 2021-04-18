package com.nusan.nusanapi.validate;

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
        ValidationUtils.rejectIfEmpty(errors,"name","employees.name", "Name must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"lastName","employees.lastName", "Last name must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"dni","employees.dni", "DNI must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"phoneNumber","employees.phoneNumber", "Phone number must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"password","employees.password", "Password must not be empty.");
    }
}
