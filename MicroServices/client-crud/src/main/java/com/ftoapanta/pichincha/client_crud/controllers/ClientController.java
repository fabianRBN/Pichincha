package com.ftoapanta.pichincha.client_crud.controllers;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.entities.Person;
import com.ftoapanta.pichincha.client_crud.services.ClientService;
import com.ftoapanta.pichincha.client_crud.services.PersonService;
import com.ftoapanta.pichincha.client_crud.utils.ResponseMap;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = "client")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientRequestDTO clientRequestDTO) {

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

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> readById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.readById(id));
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO> readAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try {
            Page<Client> clientList = clientService.readAll( PageRequest.of(page, size));
            baseResponseDTO.setData(ResponseMap.getInstance().clientMapResponse(clientList));
            baseResponseDTO.setSuccess(true);
            baseResponseDTO.setTotalElements(clientList.getTotalElements());
        }catch (Exception e) {
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
        }

        return ResponseEntity.ok(baseResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientRequestDTO clientRequestDTO) {

        Person person = Person.builder()
                .name(clientRequestDTO.getName())
                .age(clientRequestDTO.getAge())
                .address(clientRequestDTO.getAddress())
                .gender(clientRequestDTO.getGender())
                .identification(clientRequestDTO.getIdentification())
                .phoneNumber(clientRequestDTO.getPhoneNumber())
                .build();
        Client client = Client.builder()
                .person(person)
                .password(clientRequestDTO.getPassword())
                .status(true)
                .build();
        client = clientService.update(id, client);
        personService.update(client.getPerson().getId(), person);

        return ResponseEntity.ok(clientService.update(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponseDTO> searchClients(
            @RequestParam("name") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = clientService.searchClientsByName(name, pageable);
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setData(ResponseMap.getInstance().clientMapResponse(clients));
        baseResponseDTO.setTotalElements(clients.getTotalElements());
        baseResponseDTO.setSuccess(!clients.isEmpty());
        return ResponseEntity.ok(baseResponseDTO);
    }

    @GetMapping("/findByPersonIdentification")
    public ResponseEntity<BaseResponseDTO> findByPersonIdentification(@RequestParam("identification") String identification) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();

        try{
            Client client = clientService.findByPersonIdentification(identification);
            ClientResponseDTO clientResponseDTO = ClientResponseDTO.builder()
                    .identification(client.getPerson().getIdentification())
                    .age(client.getPerson().getAge())
                    .status(client.getStatus())
                    .name(client.getPerson().getName())
                    .phoneNumber(client.getPerson().getPhoneNumber())
                    .gender(client.getPerson().getGender())
                    .address(client.getPerson().getAddress())
                    .id(client.getId())
                    .build();


            baseResponseDTO.setData(clientResponseDTO);
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setData(null);
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
        }


        return ResponseEntity.ok(baseResponseDTO);
    }
}
