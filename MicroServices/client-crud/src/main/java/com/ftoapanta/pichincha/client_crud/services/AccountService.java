package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService  {
    Account createAccount(Account account);
    List<Account> getAccountsByClientId(Long clientId);
    Optional<Account> getAccountById(Long accountId);
    void deleteAccount(Long accountId);
    Account update(Long id ,Account account);
}
