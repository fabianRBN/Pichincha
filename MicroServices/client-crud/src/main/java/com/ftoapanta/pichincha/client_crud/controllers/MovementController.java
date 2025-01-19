package com.ftoapanta.pichincha.client_crud.controllers;

import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.MovementResponseDTO;
import com.ftoapanta.pichincha.client_crud.dto.MovementResquestDTO;
import com.ftoapanta.pichincha.client_crud.entities.Account;
import com.ftoapanta.pichincha.client_crud.entities.Movement;
import com.ftoapanta.pichincha.client_crud.services.AccountService;
import com.ftoapanta.pichincha.client_crud.services.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "transaction")
public class MovementController {
    private final MovementService movementService;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> createTransaction(@RequestBody MovementResquestDTO movementResquestDTO) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        Optional<Account> account = accountService.getAccountById(movementResquestDTO.getAccountId());
        if(account.isPresent()) {
            Double monto = account.get().getInitialBalance() + movementResquestDTO.getValue();

            if(monto < 0 ){
                baseResponseDTO.setSuccess(false);
                baseResponseDTO.setMessage("Invalid balance");
                return ResponseEntity.ok(baseResponseDTO);
            }

            if( movementResquestDTO.getValue() == 0){
                baseResponseDTO.setSuccess(false);
                baseResponseDTO.setMessage("Value cannot be zero");
                return ResponseEntity.ok(baseResponseDTO);
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

            baseResponseDTO.setData(movementService.createTransaction(movement));

            account.get().setInitialBalance(account.get().getInitialBalance() + movementResquestDTO.getValue());
            account.get().setInitialBalance(monto);
            accountService.update(account.get().getId(),account.get());

            baseResponseDTO.setSuccess(true);
        }
        return ResponseEntity.ok(baseResponseDTO);
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Movement>> getMovementsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(movementService.getTransactionsByAccountId(accountId));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<BaseResponseDTO> getMovementsByClientId(@PathVariable Long clientId) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            List<MovementResponseDTO> movements = movementService.getMovementsByClientId(clientId);
            baseResponseDTO.setData(movements);
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setSuccess(false);
            baseResponseDTO.setMessage(e.getMessage());
        }


        return ResponseEntity.ok(baseResponseDTO);
    }
}
