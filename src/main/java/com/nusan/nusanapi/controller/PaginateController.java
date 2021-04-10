package com.nusan.nusanapi.controller;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.service.PaginateService;
import com.nusan.nusanapi.service.entities.Paginate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class PaginateController {

    @Autowired
    private PaginateService service;

    @RequestMapping(path = "/all1")
    public ResponseEntity<List<Client>> getAllClients(@RequestBody Paginate paginate){
        List<Client> list = service.getAllClients(paginate.getNumPage(),paginate.getSizePage(), paginate.getSortBy(),paginate.isAscending());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(path = "/all2")
    public ResponseEntity<List<Report>> getAllReports(@RequestBody Paginate paginate){
        List<Report> list = service.getAllReports(paginate.getNumPage(),paginate.getSizePage(), paginate.getSortBy(),paginate.isAscending());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
