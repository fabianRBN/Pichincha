package com.ftoapanta.pichincha.account_crud.strem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoapanta.pichincha.account_crud.entities.Person;
import com.ftoapanta.pichincha.account_crud.utils.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.ftoapanta.pichincha.account_crud.entities.Client;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class ClientEventConsumer {


    private final ObjectMapper objectMapper;
    private final ClientCache clientCache;

    public ClientEventConsumer(ObjectMapper objectMapper, ClientCache clientCache) {
        this.objectMapper = objectMapper;
        this.clientCache = clientCache;
    }

    @KafkaListener(topics = "client-events", groupId = "client-group")
    public void listenClientEvents(String message) {
        log.info("Received message: " + message);
        try {
            ClientEvent event = objectMapper.readValue(message, ClientEvent.class);
            switch (event.getAction()) {
                case "CREATE":
                    Client client = new Client();
                    Map<String, Object> data = (Map)event.getData();
                    client.setId(Long.parseLong(data.get("id").toString()));
                    client.setStatus(true);
                    client.setPassword(data.get("password").toString());

                    Person person = new Person();
                    Map<String, Object> personaMap = (Map<String, Object>)data.get("person");
                    person.setName(personaMap.get("name").toString());
                    person.setId(Long.parseLong(personaMap.get("id").toString()));
                    person.setAge(Integer.parseInt(personaMap.get("age").toString()));
                    person.setGender(Gender.valueOf(personaMap.get("gender").toString()));
                    person.setAddress(personaMap.get("address").toString());
                    person.setIdentification(personaMap.get("identification").toString());
                    person.setPhoneNumber(personaMap.get("phoneNumber").toString());

                    client.setPerson(person);
                    clientCache.addClient(client);
                    break;
                default:
                    log.warn("Unknown action: {}", event.getAction());
            }
        } catch (Exception e) {
            log.error("Error processing client event", e);
        }
    }
}
