package com.nusan.nusanapi.service;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client getReportById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Client create(Client client){
        return repository.save(client);
    }

    public Client getAllClients(){
        return (Client) repository.findAll();
    }

}
