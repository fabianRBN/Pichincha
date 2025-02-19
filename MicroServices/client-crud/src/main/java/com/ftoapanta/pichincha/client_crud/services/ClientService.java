package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    BaseResponseDTO create(ClientRequestDTO client);

    BaseResponseDTO update(Long id, ClientRequestDTO client);

    void delete(Long id);

    Client readById(Long id);

    BaseResponseDTO readAll(Pageable pageable);

    BaseResponseDTO searchClientsByName(String name, Pageable pageable);

    Client findByPersonIdentification(String identification);
}
