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

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(path = "/reportState/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportsByState(@PathVariable String state){
        List<Report> reportList = service.allReportsByState(state);

        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }
}
