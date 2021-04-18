package com.nusan.nusanapi.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ReportValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"startDate","report.startDate", "The start date must not be empty.");
        ValidationUtils.rejectIfEmpty(errors,"idCli","report.idCli", "ID Client must not be empty.");
    }
}
