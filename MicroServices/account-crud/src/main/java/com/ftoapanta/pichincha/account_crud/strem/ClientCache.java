package com.ftoapanta.pichincha.account_crud.strem;

import com.ftoapanta.pichincha.account_crud.entities.Client;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientCache {

    private final Map<Long, Client> clients = new HashMap<>();

    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    public void removeClient(Long clientId) {
        clients.remove(clientId);
    }

    public Client getClient(Long clientId) {
        return clients.get(clientId);
    }
}