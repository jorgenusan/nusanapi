package com.nusan.nusanapi.validate;

import com.nusan.nusanapi.model.Report;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"startDate","report.startDate", "The start date must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"idCli","report.idCli", "ID Client must not be empty.");

        Report report = (Report) target;


        String startDate = report.getStartDate().toString();
        String dateApointment;
        String endingDate;

        if(report.getDateApointment() == null){
            dateApointment = "";
        }else{
            dateApointment = report.getDateApointment().toString();
        }

        if(report.getEndingDate()==null){
            endingDate = "";
        }else {
            endingDate = report.getEndingDate().toString();
        }

        if(!dateApointment.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$") && !dateApointment.equals("")){
            errors.rejectValue("dateApointment","report.dateApointment","Date format xx/xx/xxx.");
        }

        if(!startDate.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$")){
            errors.rejectValue("startDate","report.startDate","Date format xx/xx/xxx.");
        }

        if(!endingDate.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$") && !endingDate.equals("")){
            errors.rejectValue("endingDate","report.endingDate","Date format xx/xx/xxx.");
        }

        if(!report.getPriority().equals("alta")&&!report.getPriority().equals("media")&&!report.getPriority().equals("baja")){
            errors.rejectValue("priority","report.priority","Priority can only be high, medium or low.");
        }

        if(!report.getState().equals("abierto")&&!report.getState().equals("pendiente")&&!report.getState().equals("cerrado")){
            errors.rejectValue("state","report.state","State can be only open, pending or cloed.");
        }

        if(!report.getPayment().toString().matches("^\\d+$")&&!report.getPayment().toString().matches("")){
            errors.rejectValue("payment","report.payment","Payment has to be numbers.");
        }

        if(!report.getPaymentMethod().equals("efectivo")&&!report.getPaymentMethod().equals("tarjeta")&&!report.getPaymentMethod().equals("")){
            errors.rejectValue("paymentMethod","report.paymentMethod","Payment method can only be cash or card.");
        }
    }
}
