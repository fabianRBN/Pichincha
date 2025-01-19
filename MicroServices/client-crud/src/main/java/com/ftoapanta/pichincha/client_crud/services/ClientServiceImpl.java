package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.repositories.ClientRepository;
import com.ftoapanta.pichincha.client_crud.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
@AllArgsConstructor //lombok
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;

    public Client create(Client client) {
        personRepository.findById(client.getPerson().getId())
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + client.getPerson().getId()));
        return clientRepository.save(client);
    }

    public Client update(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id: " + id));
        existingClient.setPassword(client.getPassword());
        existingClient.setStatus(client.getStatus());
        return clientRepository.save(existingClient);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public Client readById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id: " + id));
    }

    public Page<Client> readAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }


    public Page<Client> searchClientsByName(String name, Pageable pageable) {
        return clientRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Client findByPersonIdentification(String identification) {
        return clientRepository.findByPersonIdentification(identification)
                .orElseThrow(() -> new NoSuchElementException("Client not found with identification: " + identification));
    }


}
