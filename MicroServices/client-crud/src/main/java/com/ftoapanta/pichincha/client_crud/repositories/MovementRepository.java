package com.ftoapanta.pichincha.client_crud.repositories;

import com.ftoapanta.pichincha.client_crud.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountId(Long accountId);
    List<Movement> findByAccountClientId(Long clientId);
}
