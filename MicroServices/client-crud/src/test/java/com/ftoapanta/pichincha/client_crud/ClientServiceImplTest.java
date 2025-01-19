package com.ftoapanta.pichincha.client_crud;

import com.ftoapanta.pichincha.client_crud.dto.ClientResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.entities.Person;
import com.ftoapanta.pichincha.client_crud.repositories.ClientRepository;
import com.ftoapanta.pichincha.client_crud.repositories.PersonRepository;
import com.ftoapanta.pichincha.client_crud.services.ClientServiceImpl;
import com.ftoapanta.pichincha.client_crud.services.PersonServiceImpl;
import com.ftoapanta.pichincha.client_crud.utils.Gender;
import com.ftoapanta.pichincha.client_crud.utils.ResponseMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClientServiceImplTest {

    List<Client> clients = new ArrayList<>();
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Person person = Person
                .builder()
                .name("Fabian Toapanta")
                .address("Quito")
                .gender(Gender.M)
                .phoneNumber("0995793156")
                .identification("0503625600")
                .age(12)
                .id(1L)
                .build();
        Client client = Client
                .builder()
                .person(person)
                .password("123123")
                .status(true)
                .id(1L)
                .build();
        clients.add(client);
    }

    @Test
    void testGetFindAll() {
        given(clientRepository.findAll())
                .willReturn(clients);
        Pageable pageable =  PageRequest.of(0, 10);
        Page<Client> clientsPage = clientService.readAll(pageable);
        List<ClientResponseDTO> clientResponseDTOList = new ArrayList<>();
        if(Objects.nonNull(clientsPage)){
             clientResponseDTOList = ResponseMap.getInstance().clientMapResponse(clientsPage);
        }
        assertEquals(0, clientResponseDTOList.size());
    }

}
