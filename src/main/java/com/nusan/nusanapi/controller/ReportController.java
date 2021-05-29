package com.nusan.nusanapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.service.ClientService;
import com.nusan.nusanapi.service.ReportService;
import com.nusan.nusanapi.service.entities.ReportFilter;
import com.nusan.nusanapi.validate.ReportValidate;
import org.dom4j.io.SAXEventRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private ClientService clientService;

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
            case 3: //userId
                if(filtro.matches("^[0-9]+$")){
                    int id = Integer.parseInt(filtro);
                    reportList = service.allReportsByUserId(id);
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + field);
        }

        return new ResponseEntity<>(reportList, HttpStatus.OK);

    }
/*
    @RequestMapping(path = "/reportFilter", method = RequestMethod.POST)
    public ResponseEntity<List<Report>> getReportsByState(@RequestBody ReportFilter filtro){

        StringBuilder sb = new StringBuilder();
        sb.append("1=1");

        if(filtro.getState()!=null && filtro.getState()!=""){
            sb.append(" AND state = '"+filtro.getState()+"'");
        }
        if(filtro.getPriority()!=null && filtro.getPriority()!=""){
            sb.append(" AND priority = '"+filtro.getPriority()+"'");
        }
        if(filtro.getStartDate()!=null){
            sb.append(" AND start_date = '"+filtro.getStartDate()+"'");
        }
        if(filtro.getEndDate()!=null){
            sb.append(" AND ending_date = '"+filtro.getEndDate()+"'");
        }
        if(filtro.getAppointmentDate()!=null){
            sb.append(" AND date_apointment = '"+filtro.getAppointmentDate()+"'");
        }
        if(filtro.getClientDni()!=null && filtro.getClientDni()!=""){
            sb.append(" AND c.dni = '"+filtro.getClientDni()+"'");
        }
        if(filtro.getClientId()!=null && filtro.getClientId()!="") {
            sb.append(" AND cli_id = " + filtro.getEmployeeId());
        }
        if(filtro.getEmployeeId()!=null && filtro.getEmployeeId()!="") {
            sb.append(" AND emp_id = " + filtro.getEmployeeId());
        }
        String str = sb.toString();
        List<Report> reportList = service.allReportsByFilters(str);

        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

 */
}
