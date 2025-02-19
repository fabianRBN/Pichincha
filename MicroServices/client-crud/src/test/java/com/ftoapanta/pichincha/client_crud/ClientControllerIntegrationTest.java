package com.ftoapanta.pichincha.client_crud;

import com.ftoapanta.pichincha.client_crud.dto.ClientRequestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.repositories.ClientRepository;
import com.ftoapanta.pichincha.client_crud.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {

        //clientRepository.deleteAll();
        //personRepository.deleteAll();
    }

    @Test
    void testCreateClient() throws Exception {
        String requestBody = """
                {
                            "id": 100,
                            "name": "Juan Pérez",
                            "address": "Av. Siempre Viva 123",
                            "gender": "M",
                            "age": 35,
                            "phoneNumber": "0987654321",
                            "status": true,
                            "identification":"0503625600",
                            "password":"23423456"
                }
        """;
        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }



    @Test
    void testUpdateClient() throws Exception {
        String requestBody = """
                {
                            "name": "Juan Pérez",
                            "address": "Av. Siempre Viva 123",
                            "gender": "M",
                            "age": 35,
                            "phoneNumber": "0987654321",
                            "status": true,
                            "identification":"0503625600",
                            "password":"23423456"
                }
        """;

        // Realizar la solicitud PUT
        mockMvc.perform(MockMvcRequestBuilders.put("/client/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testDeleteClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/client/{id}", 18L))
                .andExpect(status().isOk());
    }
}
