package com.ftoapanta.pichincha.account_crud.services;

import com.ftoapanta.pichincha.account_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.account_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.account_crud.dto.MovementResquestDTO;
import com.ftoapanta.pichincha.account_crud.entities.Movement;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MovementService {
    BaseResponseDTO createTransaction(MovementResquestDTO movementResquestDTO);
    List<Movement> getTransactionsByAccountId(Long accountId);
    List<MovementResponseDTO> getMovementsByClientId(Long clientId);
    Map<String,Object> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);

}
