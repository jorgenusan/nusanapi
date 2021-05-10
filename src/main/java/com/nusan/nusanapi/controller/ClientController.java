package com.nusan.nusanapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.service.ClientService;
import com.nusan.nusanapi.validate.CLientValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ClientController {

    @Autowired
    private ClientService service;


    @Autowired
    CLientValidate validator;


    @RequestMapping(path = "/client", method = RequestMethod.POST)
    public ResponseEntity<Client> createClients(@RequestBody Client client, BindingResult result){

        validator.validate(client, result);
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        if(!service.isDniUsed(client.getDni())){
            Client clientCreated = service.createClient(client);
            return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @RequestMapping(path = "/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> geClientById(@PathVariable Long id){
        return ResponseEntity.ok(service.getClientById(id));

    }
    @RequestMapping(path = "/clientdni/{dni}", method = RequestMethod.GET)
    public ResponseEntity<Client> geClientByDni(@PathVariable String dni){
        String dniCli = dni.replace(" ","");
        return ResponseEntity.ok(service.getClientByDni(dniCli));
    }

    @RequestMapping(path = "/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteClient(@PathVariable Long id){
        if(!service.existsById(id)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            service.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/clientdni/{dni}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteClient(@PathVariable String dni){
        String dniCli = dni.replace(" ","");
        if(!service.existsByDni(dniCli)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            Client cli = service.getClientByDni(dniCli);
            service.deleteClientById(cli.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping(path = "/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody JsonPatch patch){

        if(!service.existsById(id)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        try{
            Client client = service.getClientById(id);
            Client clientPatch = service.applyPatchToUser(patch, client);
            service.createClient(clientPatch);
            return ResponseEntity.ok(clientPatch);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}