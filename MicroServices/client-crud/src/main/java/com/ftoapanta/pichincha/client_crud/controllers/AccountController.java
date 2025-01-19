package com.ftoapanta.pichincha.client_crud.controllers;

import com.ftoapanta.pichincha.client_crud.dto.AccountRequestDTO;
import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.client_crud.entities.Account;
import com.ftoapanta.pichincha.client_crud.entities.Client;
import com.ftoapanta.pichincha.client_crud.services.AccountService;
import com.ftoapanta.pichincha.client_crud.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "account")
public class AccountController {

    private final AccountService accountService;

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            Client client = clientService.readById(accountRequestDTO.getClientId());

            Account account = Account.builder()
                    .status(true)
                    .accountNumber(accountRequestDTO.getAccountNumber())
                    .accountType(accountRequestDTO.getAccountType())
                    .initialBalance(accountRequestDTO.getInitialBalance())
                    .client(client )
                    .build();

            baseResponseDTO.setData(accountService.createAccount(account));
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setSuccess(Boolean.FALSE);
            baseResponseDTO.setMessage(e.getMessage());
        }



        return ResponseEntity.ok(baseResponseDTO);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<BaseResponseDTO> getAccountsByClientId(@PathVariable Long clientId) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();

        try{
            baseResponseDTO.setData(accountService.getAccountsByClientId(clientId));
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setMessage(e.getMessage());
            baseResponseDTO.setSuccess(false);
        }

        return ResponseEntity.ok(baseResponseDTO);
    }


    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<BaseResponseDTO> updateAccount(@PathVariable Long accountId,@RequestBody AccountRequestDTO accountRequestDTO) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            Optional<Account> account = accountService.getAccountById(accountId);

            if(account.isPresent()){
                account.get().setStatus(false);
                account.get().setId(accountId);
                accountService.update(accountId, account.get());
            }



            baseResponseDTO.setData(accountService.createAccount(account.orElse(null)));
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setSuccess(Boolean.FALSE);
            baseResponseDTO.setMessage(e.getMessage());
        }



        return ResponseEntity.ok(baseResponseDTO);
    }
}