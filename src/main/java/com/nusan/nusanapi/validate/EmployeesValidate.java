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

        if(!employees.getPassword().matches("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")){
            errors.rejectValue("password","employees.password","La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula. Puede tener otros símbolos.");
        }

        if(!employees.getRol().equals("admin")){
            if(!employees.getRol().equals("empleado")){
                errors.rejectValue("rol","employees.rol","The status can only be admin or empleado");
            }
        }
    }
}
