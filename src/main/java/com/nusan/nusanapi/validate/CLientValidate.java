package com.nusan.nusanapi.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CLientValidate implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"Client.name", "Name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"Client.lastName", "Last name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"Client.dni", "DNI must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"Client.phoneNumber", "Phone number must not be empty.");
    }
}
