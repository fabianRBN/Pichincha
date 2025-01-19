package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Movement;

import java.util.List;

public interface MovementService {
    Movement createTransaction(Movement transaction);
    List<Movement> getTransactionsByAccountId(Long accountId);
    List<MovementResponseDTO> getMovementsByClientId(Long clientId);
}
