package com.ftoapanta.pichincha.client_crud;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.entities.Person;
import com.ftoapanta.pichincha.client_crud.repositories.ClientRepository;
import com.ftoapanta.pichincha.client_crud.services.ClientServiceImpl;
import com.ftoapanta.pichincha.client_crud.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;


public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PersonService personService;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClient() {
        // Arrange
        ClientRequestDTO requestDTO = new ClientRequestDTO();
        requestDTO.setName("John Doe");
        requestDTO.setAge(30);
        requestDTO.setIdentification("1234567890");

        Person person = new Person();
        person.setId(1L);

        Client client = Client
                .builder()
                .person(person)
                .password("123123")
                .status(true)
                .id(1L)
                .build();


        when(personService.create(any(Person.class))).thenReturn(person);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        BaseResponseDTO response = clientService.create(requestDTO);
        assertNotNull(response);
        assertTrue(response.isSuccess());
        verify(personService, times(1)).create(any(Person.class));
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testReadById() {
        // Arrange
        Long id = 1L;
        Client client = new Client();
        client.setId(id);
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        Client result = clientService.readById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(clientRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateClient() {
        Long id = 1L;
        ClientRequestDTO requestDTO = new ClientRequestDTO();
        requestDTO.setName("Updated Name");

        Client existingClient = new Client();
        existingClient.setId(id);
        Person person = new Person();
        person.setId(1L);
        existingClient.setPerson(person);

        when(clientRepository.findById(id)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(existingClient);
        BaseResponseDTO response = clientService.update(id, requestDTO);
        assertNotNull(response);
        assertTrue(response.isSuccess());
        verify(clientRepository, times(1)).findById(id);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClient() {
        Long id = 1L;
        doNothing().when(clientRepository).deleteById(id);
        clientService.delete(id);
        verify(clientRepository, times(1)).deleteById(id);
    }

}
