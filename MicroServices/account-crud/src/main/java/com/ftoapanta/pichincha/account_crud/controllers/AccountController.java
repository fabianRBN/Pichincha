package com.ftoapanta.pichincha.account_crud.controllers;

import com.ftoapanta.pichincha.account_crud.dto.AccountRequestDTO;
import com.ftoapanta.pichincha.account_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.account_crud.entities.Account;
import com.ftoapanta.pichincha.account_crud.entities.Client;
import com.ftoapanta.pichincha.account_crud.services.AccountService;
import com.ftoapanta.pichincha.account_crud.services.ClientService;
import com.ftoapanta.pichincha.account_crud.strem.ClientCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping(path = "account")
public class AccountController {

    private final AccountService accountService;

    private final ClientService clientService;
    private final ClientCache clientCache;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        log.info("Creando createAccount: {}", accountRequestDTO);
        return ResponseEntity.ok(accountService.createAccount(accountRequestDTO));
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


        return ResponseEntity.ok(accountService.update(accountId, accountRequestDTO));
    }
}