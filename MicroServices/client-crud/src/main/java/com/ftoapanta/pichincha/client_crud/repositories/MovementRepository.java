package com.ftoapanta.pichincha.client_crud.repositories;


import com.ftoapanta.pichincha.client_crud.entities.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountId(Long accountId);
    List<Movement> findByAccountClientId(Long clientId);
    Page<Movement> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);

}
