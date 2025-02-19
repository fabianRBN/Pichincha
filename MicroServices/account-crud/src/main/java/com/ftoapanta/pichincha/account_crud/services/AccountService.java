package com.ftoapanta.pichincha.account_crud.services;

import com.ftoapanta.pichincha.account_crud.dto.AccountRequestDTO;
import com.ftoapanta.pichincha.account_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.account_crud.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService  {
    BaseResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    List<Account> getAccountsByClientId(Long clientId);
    Optional<Account> getAccountById(Long accountId);
    void deleteAccount(Long accountId);
    BaseResponseDTO update(Long id, AccountRequestDTO accountRequestDTO);
}
