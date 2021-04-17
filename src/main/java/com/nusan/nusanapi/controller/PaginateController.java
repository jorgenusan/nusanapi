package com.nusan.nusanapi.controller;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.model.Employees;
import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.service.PaginateService;
import com.nusan.nusanapi.service.entities.Paginate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping
public class PaginateController {

    @Autowired
    private PaginateService service;

    @RequestMapping(path = "/allClients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients(@RequestBody Paginate paginate){
        List<Client> list = service.getAllClients(paginate.getNumPage(),paginate.getSizePage(), paginate.getSortBy(),paginate.isAscending());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(path = "/allEmployees", method = RequestMethod.GET)
    public ResponseEntity<List<Employees>> getAllEmployees(@RequestBody Paginate paginate){
        List<Employees> list = service.getAllEmployees(paginate.getNumPage(),paginate.getSizePage(), paginate.getSortBy(),paginate.isAscending());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(path = "/allReports", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getAllReports(@RequestBody Paginate paginate){
        List<Report> list = service.getAllReports(paginate.getNumPage(),paginate.getSizePage(), paginate.getSortBy(),paginate.isAscending());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
