package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    Client create(Client client);

    Client update(Long id, Client client);

    void delete(Long id);

    Client readById(Long id);

    Page<Client> readAll(Pageable pageable);

    Page<Client> searchClientsByName(String name, Pageable pageable);

    Client findByPersonIdentification(String identification);
}
