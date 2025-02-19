package com.ftoapanta.pichincha.account_crud.services;

import com.ftoapanta.pichincha.account_crud.dto.AccountRequestDTO;
import com.ftoapanta.pichincha.account_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.account_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.account_crud.dto.MovementResquestDTO;
import com.ftoapanta.pichincha.account_crud.entities.Account;
import com.ftoapanta.pichincha.account_crud.entities.Movement;
import com.ftoapanta.pichincha.account_crud.repositories.MovementRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor //lombok
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final AccountService accountService;


    @Override
    public BaseResponseDTO createTransaction(MovementResquestDTO movementResquestDTO) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        Optional<Account> account = accountService.getAccountById(movementResquestDTO.getAccountId());
        if(account.isPresent()) {
            Double monto = account.get().getInitialBalance() + movementResquestDTO.getValue();

            if(monto < 0 ){
                baseResponseDTO.setSuccess(false);
                baseResponseDTO.setMessage("Invalid balance");

            }

            if( movementResquestDTO.getValue() == 0){
                baseResponseDTO.setSuccess(false);
                baseResponseDTO.setMessage("Value cannot be zero");

            }

            if(movementResquestDTO.getValue() > 0){
                movementResquestDTO.setTransactionType("C");
            }else{
                movementResquestDTO.setTransactionType("D");
            }
            Movement movement = Movement.builder()
                    .date(LocalDate.now())
                    .value(movementResquestDTO.getValue())
                    .transactionType(movementResquestDTO.getTransactionType())
                    .available(monto)
                    .account(account.get())
                    .balance(account.get().getInitialBalance())
                    .build();

            baseResponseDTO.setData(this.movementRepository.save(movement));


            account.get().setInitialBalance(monto);
            accountService.update(account.get().getId(),
                    AccountRequestDTO.builder()
                            .id(account.get().getId())
                            .accountNumber(account.get().getAccountNumber())
                            .accountType(account.get().getAccountType())
                            .initialBalance(account.get().getInitialBalance())
                            .accountType(account.get().getAccountType())
                            .status(account.get().getStatus())
                            .build());

            baseResponseDTO.setSuccess(true);
        }

        return baseResponseDTO;


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
