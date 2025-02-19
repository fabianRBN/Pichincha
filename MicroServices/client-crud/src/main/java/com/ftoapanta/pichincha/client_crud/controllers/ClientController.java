package com.ftoapanta.pichincha.client_crud.controllers;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.services.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "client")
@CrossOrigin
@Validated
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> create( @Valid @RequestBody ClientRequestDTO client) {
        log.info("Creando cliente con identificación: {}", client.getIdentification());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> readById(@PathVariable Long id) {
        log.info("Buscando cliente con ID: {}", id);
        return ResponseEntity.ok(clientService.readById(id));
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO> readAll(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "La página no puede ser menor que 0") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "El tamaño debe ser al menos 1")
            @Max(value = 100, message = "El tamaño no puede ser mayor que 100") int size) {
        log.info("Obteniendo lista de clientes - Página: {}, Tamaño: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clientService.readAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO client) {
        log.info("Actualizando cliente con ID: {}", id);
        return ResponseEntity.ok(clientService.update(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Eliminando cliente con ID: {}", id);
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponseDTO> searchClients(@RequestParam("name") String name,
                                                         @RequestParam(defaultValue = "0")
                                                         @Min(value = 0, message = "La página no puede ser menor que 0") int page,
                                                         @RequestParam(defaultValue = "10") @Min(value = 1, message = "El tamaño debe ser al menos 1")
                                                             @Max(value = 100, message = "El tamaño no puede ser mayor que 100") int size) {
        log.info("Buscando clientes por nombre: {}", name);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clientService.searchClientsByName(name, pageable));
    }

    @GetMapping("/findByPersonIdentification")
    public ResponseEntity<Client> findByPersonIdentification(@RequestParam("identification") String identification) {
        log.info("Buscando cliente con identificación: {}", identification);
        return ResponseEntity.ok(clientService.findByPersonIdentification(identification));
    }
}
