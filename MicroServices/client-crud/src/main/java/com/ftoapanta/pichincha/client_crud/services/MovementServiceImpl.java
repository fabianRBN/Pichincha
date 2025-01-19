package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Movement;
import com.ftoapanta.pichincha.client_crud.repositories.MovementRepository;
import com.ftoapanta.pichincha.client_crud.utils.ResponseMap;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor //lombok
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;

    @Override
    public Movement createTransaction(Movement movement) {
        return this.movementRepository.save(movement);
    }

    @Override
    public List<Movement> getTransactionsByAccountId(Long accountId) {
        return this.movementRepository.findByAccountId(accountId);
    }
    @Override
    public List<MovementResponseDTO> getMovementsByClientId(Long clientId) {
        return movementRepository.findByAccountClientId(clientId).stream().map(movement -> {
            MovementResponseDTO dto = new MovementResponseDTO();
            dto.setFecha(movement.getDate().toString());
            dto.setCliente(movement.getAccount().getClient().getPerson().getName());
            dto.setNumeroCuenta(movement.getAccount().getAccountNumber());
            dto.setTipo(movement.getAccount().getAccountType());
            dto.setSaldoInicial(BigDecimal.valueOf(movement.getBalance()));
            dto.setEstado(movement.getAccount().getStatus());
            dto.setMovimiento(BigDecimal.valueOf(movement.getValue()));
            dto.setSaldoDisponible(BigDecimal.valueOf(movement.getAvailable()));
            dto.setAvailable(movement.getAvailable());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String,Object> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable) {

        Map<String,Object> map = new HashMap<>();

        Page<Movement> movementPage = movementRepository.findByDateBetween(start,end,pageable);

        List<MovementResponseDTO> list = movementPage.stream().map(movement -> {
            MovementResponseDTO dto = new MovementResponseDTO();
            dto.setFecha(movement.getDate().toString());
            dto.setCliente(movement.getAccount().getClient().getPerson().getName());
            dto.setNumeroCuenta(movement.getAccount().getAccountNumber());
            dto.setTipo(movement.getAccount().getAccountType());
            dto.setSaldoInicial(BigDecimal.valueOf(movement.getBalance()));
            dto.setEstado(movement.getAccount().getStatus());
            dto.setMovimiento(BigDecimal.valueOf(movement.getValue()));
            dto.setSaldoDisponible(BigDecimal.valueOf(movement.getAvailable()));
            dto.setAvailable(movement.getAvailable());
            return dto;
        }).collect(Collectors.toList());

        map.put("result",list);
        map.put("total",movementPage.getTotalElements());
        return map ;
    }


}
