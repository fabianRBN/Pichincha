package com.ftoapanta.pichincha.client_crud;


import com.ftoapanta.pichincha.client_crud.controllers.ClientController;
import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;

import com.ftoapanta.pichincha.client_crud.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClient() {
        ClientRequestDTO requestDTO = new ClientRequestDTO();
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setSuccess(true);

        when(clientService.create(any(ClientRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<BaseResponseDTO> response = clientController.create(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().isSuccess());
        verify(clientService, times(1)).create(any(ClientRequestDTO.class));
    }

    @Test
    void testReadById() {

        Long id = 1L;
        Client client = new Client();
        client.setId(id);
        when(clientService.readById(id)).thenReturn(client);
        ResponseEntity<Client> response = clientController.readById(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        verify(clientService, times(1)).readById(id);
    }

    @Test
    void testUpdateClient() {
        Long id = 1L;
        ClientRequestDTO requestDTO = new ClientRequestDTO();
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setSuccess(true);
        when(clientService.update(id, requestDTO)).thenReturn(responseDTO);
        ResponseEntity<BaseResponseDTO> response = clientController.update(id, requestDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isSuccess());
        verify(clientService, times(1)).update(id, requestDTO);
    }

    @Test
    void testDeleteClient() {
        Long id = 1L;
        doNothing().when(clientService).delete(id);
        ResponseEntity<Void> response = clientController.delete(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clientService, times(1)).delete(id);
    }
}
