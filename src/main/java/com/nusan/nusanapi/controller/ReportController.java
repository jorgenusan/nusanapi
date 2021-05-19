package com.nusan.nusanapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.service.ReportService;
import com.nusan.nusanapi.validate.ReportValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    ReportValidate validate;


    @RequestMapping(path = "/report", method = RequestMethod.POST)
    public ResponseEntity<Report> createReport(@RequestBody Report report, BindingResult result){
        validate.validate(report, result);
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Report reportCreated = service.createReport(report);
        return new ResponseEntity<>(reportCreated, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/report/{id}", method = RequestMethod.GET)
    public ResponseEntity<Report> getReportById(@PathVariable Long id){
        Report report = service.getReportById(id);
        if(report != null){
            return ResponseEntity.ok(report);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @RequestMapping(path = "/report/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Report> deleteReport(@PathVariable Long id){
        if(!service.existsById(id)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            service.deleteReportById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping(path = "/report/{id}")
    public ResponseEntity<Report> updateEmployee(@PathVariable long id, @RequestBody JsonPatch patch, BindingResult result){
        if(!service.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try{
            Report report = service.getReportById(id);
            Report reportPatch = service.applyPatchToUser(patch, report);
            service.createReport(reportPatch);
            return ResponseEntity.ok(reportPatch);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/reportFilter/{filtro}/field/{field}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportsByState(@PathVariable String filtro,@PathVariable int field){
        List<Report> reportList = null;
        switch(field){
            case 1: //state

                if(filtro.equals("Abierto")||filtro.equals("Cerrado")||filtro.equals("Pendiente")){
                    reportList = service.allReportsByState(filtro);
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                
                break;
            case 2: //endingDate

                if(filtro.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$")){
                    reportList = service.allReportsByDateEnd(filtro);
                }else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                
                break;
            case 3: //dateAppointment

                if(filtro.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$")){
                    reportList = service.allReportsByDateAppointment(filtro);
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                break;
            case 4: //startDate

                if(filtro.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$")){
                    reportList = service.allReportsByDateStart(filtro);
                }else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                break;
            case 5: //priority

                if(filtro.equals("Baja")||filtro.equals("Media")||filtro.equals("Alta")){
                    reportList = service.allReportsByPriority(filtro);
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                break;
            case 6: //client.lastName

                List<Integer> idClient = service.allReportsByClientLastName(filtro);

                for(int x = 0; x<idClient.size();x++){
                    reportList = service.allReportsByIdClient(idClient.get(x));
                }

                break;
            case 7: //client.dni
                if(filtro.matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$")){
                    List<Integer> idClientDni = service.allReportsByClientDNI(filtro);

                    for(int x = 0; x<idClientDni.size();x++){
                        reportList = service.allReportsByIdClient(idClientDni.get(x));
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                break;
            case 8: //employee.lastName

                List<Integer> idEmployee = service.allReportsByEmployeeLastName(filtro);

                for(int x = 0; x<idEmployee.size();x++){
                    reportList = service.allReportsByIdEmployee(idEmployee.get(x));
                }

                break;
            case 9: //employee.dni

                if(filtro.matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$")){
                    List<Integer> idEmployeeDni = service.allReportsByEmployeeDNI(filtro);

                    for(int x = 0; x<idEmployeeDni.size();x++){
                        reportList = service.allReportsByIdEmployee(idEmployeeDni.get(x));
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + field);
        }
        
        return new ResponseEntity<>(reportList, HttpStatus.OK);

    }
}
