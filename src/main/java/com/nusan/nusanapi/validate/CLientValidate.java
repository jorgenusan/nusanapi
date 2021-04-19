package com.nusan.nusanapi.validate;

import com.nusan.nusanapi.model.Client;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","client.name", "Name must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"lastName","client.lastName", "Last name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"dni","client.dni", "DNI must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNumber","client.phoneNumber", "Phone number must not be empty.");

        Client client = (Client) target;

         String number = client.getPhoneNumber().toString();
        if(!number.matches("^\\d{9}$")){
            errors.rejectValue("phoneNumber","client.phoneNumber","Phone number  must have 9 digits.");
        }

        if(!client.getEmail().matches("^(.+\\@.+\\..+)$")){
            errors.rejectValue("email","client.email","Email not valid.");
        }

        if(!client.getDni().matches("^\\d{8}(?:[-\\s]\\d{4})?$")){
            errors.rejectValue("dni","client.dni","DNI is not valid.");
        }

    }
}
