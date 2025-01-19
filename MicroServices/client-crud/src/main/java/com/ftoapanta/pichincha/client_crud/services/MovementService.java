package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Movement;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MovementService {
    Movement createTransaction(Movement transaction);
    List<Movement> getTransactionsByAccountId(Long accountId);
    List<MovementResponseDTO> getMovementsByClientId(Long clientId);
    Map<String,Object> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);

}
