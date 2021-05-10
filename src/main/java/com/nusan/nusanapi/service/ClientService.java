package com.nusan.nusanapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public Client getClientById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Client getClientByDni(String dni){return repository.findByDni(dni);}

    public Client createClient(Client client){return repository.save(client);}

    public Boolean isDniUsed(String dni){return repository.existsByDni(dni);}

    public void deleteClientById(Long id){repository.deleteById(id);}

    public void deleteClient(Client client){repository.delete(client);}

    public Boolean existsById(Long id){return repository.existsById(id);}

    public Boolean existsByDni(String dni){return repository.existsByDni(dni);}

    public Client applyPatchToUser(JsonPatch patch, Client targetUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, Client.class);
    }
}
