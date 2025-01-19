package com.ftoapanta.pichincha.client_crud.repositories;

import com.ftoapanta.pichincha.client_crud.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);
}
