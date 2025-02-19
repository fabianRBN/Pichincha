package com.ftoapanta.pichincha.account_crud.services;

import com.ftoapanta.pichincha.account_crud.dto.AccountRequestDTO;
import com.ftoapanta.pichincha.account_crud.dto.BaseResponseDTO;
import com.ftoapanta.pichincha.account_crud.entities.Account;
import com.ftoapanta.pichincha.account_crud.entities.Client;
import com.ftoapanta.pichincha.account_crud.repositories.AccountRepository;
import com.ftoapanta.pichincha.account_crud.strem.ClientCache;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor //lombok
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final ClientCache clientCache;

    @Override
    public BaseResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{

            Client client = clientCache.getClient(accountRequestDTO.getClientId());
            if (client == null) {
                client = clientService.readById(accountRequestDTO.getClientId());
            }
            Account account = Account.builder()
                    .status(true)
                    .accountNumber(accountRequestDTO.getAccountNumber())
                    .accountType(accountRequestDTO.getAccountType())
                    .initialBalance(accountRequestDTO.getInitialBalance())
                    .client(client )
                    .build();
            baseResponseDTO.setData(accountRepository.save(account));
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setSuccess(Boolean.FALSE);
            baseResponseDTO.setMessage(e.getMessage());
        }

        return baseResponseDTO;
    }

    @Override
    public List<Account> getAccountsByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public BaseResponseDTO update(Long id, AccountRequestDTO accountRequestDTO) {

        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try{
            Optional<Account> account = accountRepository.findById(id);

            account.get().setInitialBalance(accountRequestDTO.getInitialBalance());
            account.get().setAccountNumber(accountRequestDTO.getAccountNumber());
            account.get().setAccountType(accountRequestDTO.getAccountType());
            account.get().setStatus(accountRequestDTO.getStatus());
            baseResponseDTO.setData(accountRepository.save(account.get()));
            baseResponseDTO.setSuccess(true);
        }catch (Exception e){
            baseResponseDTO.setSuccess(Boolean.FALSE);
            baseResponseDTO.setMessage(e.getMessage());
        }

        return baseResponseDTO;
    }
}