package com.ftoapanta.pichincha.account_crud.repositories;


import com.ftoapanta.pichincha.account_crud.entities.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountId(Long accountId);
    List<Movement> findByAccountClientId(Long clientId);
    Page<Movement> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);

}
