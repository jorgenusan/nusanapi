package com.nusan.nusanapi.controller;

import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.service.ReportService;
import com.nusan.nusanapi.validate.ReportValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {

    @Autowired
    private ReportService service;
/*
    @Autowired
    private ReportValidate validate;
 */

    @RequestMapping(path = "/report", method = RequestMethod.POST)
    public ResponseEntity<Report> createReport(@RequestBody Report report/*, BindingResult result*/){
        /*validate.validate(report, result);
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }*/

        Report reportCreated = service.createReport(report);
        return new ResponseEntity<>(reportCreated, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/report/{id}", method = RequestMethod.GET)
    public ResponseEntity<Report> getReportById(@PathVariable Long id){
        return ResponseEntity.ok(service.getReportById(id));
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

    @RequestMapping(path = "/report", method = RequestMethod.PUT)
    public ResponseEntity<Report> modifyReport(@RequestBody Report report){
        if(!service.existsById(report.getId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            service.deleteReport(report);
            service.createReport(report);
            return ResponseEntity.ok(service.getReportById(report.getId()));
        }
    }
}
