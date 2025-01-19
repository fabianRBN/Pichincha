package com.ftoapanta.pichincha.client_crud.utils;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ResponseMap {
    private static ResponseMap instance = null;

    public static ResponseMap getInstance() {
        if (instance == null)
            instance = new ResponseMap();
        return instance;
    }

    public List<ClientResponseDTO> clientMapResponse(Page<Client> clients){
        List<ClientResponseDTO> clientResponseDTOList = new ArrayList<>();
        clients.forEach(
                client -> {
                    ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
                    clientResponseDTO.setId(client.getId());
                    clientResponseDTO.setName(client.getPerson().getName());
                    clientResponseDTO.setAddress(client.getPerson().getAddress());
                    clientResponseDTO.setGender(client.getPerson().getGender());
                    clientResponseDTO.setAge(client.getPerson().getAge());
                    clientResponseDTO.setPhoneNumber(client.getPerson().getPhoneNumber());
                    clientResponseDTO.setStatus(client.getStatus());
                    clientResponseDTO.setIdentification(client.getPerson().getIdentification());

                    clientResponseDTOList.add(clientResponseDTO);
                }
        );

        return clientResponseDTOList;
    }
}
