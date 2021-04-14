package com.nusan.nusanapi.service;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client getReportById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Client createClient(Client client){
        return repository.save(client);
    }

}
