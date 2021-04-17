package com.nusan.nusanapi.service;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client getClientById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Client createClient(Client client){return repository.save(client);}

    public Boolean isDniUsed(String dni){return repository.existsByDni(dni);}

    public void deleteClientById(Long id){repository.deleteById(id);}

    public void deleteCLient(Client client){repository.delete(client);}

    public Boolean existsById(Long id){return repository.existsById(id);}

}
