package com.ftoapanta.pichincha.client_crud.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.entities.Person;
import com.ftoapanta.pichincha.client_crud.repositories.ClientRepository;
import com.ftoapanta.pichincha.client_crud.strem.ClientEvent;
import com.ftoapanta.pichincha.client_crud.strem.ClientEventProducer;
import com.ftoapanta.pichincha.client_crud.utils.ResponseMap;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.swing.text.Utilities;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@AllArgsConstructor //lombok
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PersonService personService;
    private final ClientEventProducer clientEventProducer;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public BaseResponseDTO create(ClientRequestDTO clientRequestDTO) {
        try {
            Person person = Person.builder()
                    .name(clientRequestDTO.getName())
                    .age(clientRequestDTO.getAge())
                    .address(clientRequestDTO.getAddress())
                    .gender(clientRequestDTO.getGender())
                    .identification(clientRequestDTO.getIdentification())
                    .phoneNumber(clientRequestDTO.getPhoneNumber())
                    .build();
            person = personService.create(person);

            Client client = Client.builder()
                    .person(person)
                    .password(clientRequestDTO.getPassword())
                    .status(true)
                    .build();

            Client client1 = clientRepository.save(client);
            publishClientEvent("CREATE", client1);
            return BaseResponseDTO.builder()
                    .data(ResponseMap.getInstance().clientResponse(client1))
                    .success(true)
                    .build();
        } catch (Exception e) {
            log.error("Error creating client: {}", e.getMessage());
            throw new RuntimeException("Error creating client", e);
        }
    }

    public Client readById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id: " + id));
    }

    public BaseResponseDTO readAll(Pageable pageable) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            Page<Client> clients = clientRepository.findAll(pageable);
            baseResponseDTO.setData(ResponseMap.getInstance().clientMapResponse(clients));
            baseResponseDTO.setSuccess(true);
            baseResponseDTO.setTotalElements(clients.getTotalElements());
        } catch (Exception e) {
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
            baseResponseDTO.setError("Error reading all clients");
        }
        return baseResponseDTO;
    }

    @Transactional
    @Override
    public BaseResponseDTO update(Long id, ClientRequestDTO clientRequestDTO) {
        try {
            Client existingClient = readById(id);

            Person person = existingClient.getPerson();
            person.setName(clientRequestDTO.getName());
            person.setAge(clientRequestDTO.getAge());
            person.setAddress(clientRequestDTO.getAddress());
            person.setGender(clientRequestDTO.getGender());
            person.setIdentification(clientRequestDTO.getIdentification());
            person.setPhoneNumber(clientRequestDTO.getPhoneNumber());
            personService.update(person.getId(), person);
            existingClient.setPassword(clientRequestDTO.getPassword());
            existingClient.setStatus(true);
            Client client1 = clientRepository.save(existingClient);
            return BaseResponseDTO.builder()
                    .data(ResponseMap.getInstance().clientResponse(client1))
                    .success(true)
                    .build();
        } catch (Exception e) {
            log.error("Error updating client: {}", e.getMessage());
            throw new RuntimeException("Error updating client", e);
        }
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public BaseResponseDTO searchClientsByName(String name, Pageable pageable) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            Page<Client> clients =  clientRepository.findByNameContainingIgnoreCase(name, pageable);
            baseResponseDTO.setData(ResponseMap.getInstance().clientMapResponse(clients));
            baseResponseDTO.setSuccess(true);
            baseResponseDTO.setTotalElements(clients.getTotalElements());
        } catch (Exception e) {
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
            baseResponseDTO.setError("Error searchClientsByName all clients");
        }
        return baseResponseDTO;
    }

    public Client findByPersonIdentification(String identification) {
        return clientRepository.findByPersonIdentification(identification)
                .orElseThrow(() -> new NoSuchElementException("Client not found with identification: " + identification));
    }

    private void publishClientEvent(String action, Object data) {
        try {
            String message = objectMapper.writeValueAsString(new ClientEvent(action, data));
            clientEventProducer.sendClientEvent(message);
        } catch (JsonProcessingException e) {
            log.error("Error serializing client event", e);
        }
    }
}
