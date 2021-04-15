package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Boolean existsByDni(String dni);

}
