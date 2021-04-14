package com.nusan.nusanapi.controller;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class ClientController {

    @Autowired
    private ClientService service;

    @RequestMapping(path = "/client", method = RequestMethod.POST)
    public ResponseEntity<Client> createClients(@RequestBody Client client){

        Client reportCreated = service.createClient(client);
        return new ResponseEntity<>(reportCreated, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> geClientById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getReportById(id));
    }

}