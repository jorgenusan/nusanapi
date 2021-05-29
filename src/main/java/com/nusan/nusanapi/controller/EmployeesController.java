package com.nusan.nusanapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.email.EmailSender;
import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.service.EmployeesService;
import com.nusan.nusanapi.validate.EmployeesValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeesService service;

    @Autowired
    EmployeesValidate validate;
    /*
    @Autowired
    private JavaMailSender emailSender;*/

    private final EmailSender emailSender;

    public EmployeesController(EmployeesService service, EmailSender emailSender) {
        this.service = service;
        this.emailSender = emailSender;
    }


    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees, BindingResult result){
        validate.validate(employees, result);
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if(!service.existEmployeeByDni(employees.getDni())){
            Employees employeesCreated = service.create(employees);
            return new ResponseEntity<>(employeesCreated, HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id){
        Employees employees = service.getEmployeeById(id);
        if(employees != null){
            return  ResponseEntity.ok(service.getEmployeeById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(path = "/employeesDni/{dni}", method = RequestMethod.GET)
    public ResponseEntity<Employees> getEmployeeByDni(@PathVariable String dni){
        String dniEmp = dni.replace(" ","");
        Employees employees = service.getEmployeeByDni(dniEmp);
        if(employees != null){
            return ResponseEntity.ok(employees);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(path = "/employeesEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<Employees> getEmployeeByEmail(@PathVariable String email){
        Employees employees = service.findByEmail(email);
        if(employees != null){
            return ResponseEntity.ok(employees);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employees> deleteEmployeeById(@PathVariable Long id){
        if(!service.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            service.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/employeesDni/{dni}", method = RequestMethod.DELETE)
    public ResponseEntity<Employees> deleteEmployeeById(@PathVariable String dni){
        if(!service.existEmployeeByDni(dni)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Employees employees = service.getEmployeeByDni(dni);
            service.deleteEmployeeById(employees.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping(path = "/employees/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable long id, @RequestBody JsonPatch patch){
        if(!service.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try{
            Employees employees = service.getEmployeeById(id);
            Employees employeePatch = service.applyPatchToUser(patch, employees);
            service.create(employeePatch);
            return ResponseEntity.ok(employeePatch);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
/*
    @RequestMapping(path = "/forgot-password/{email}")
    public ResponseEntity<Employees> forgotEmployeePassword(@PathVariable String email){
        Employees employees = service.findByEmail(email);
        if(employees != null){
            String link = "http://google.com/";
            emailSender.send(employees.getEmail(), buildEmail(employees.getName(),link));

            return new ResponseEntity<>(employees, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }*/
}
