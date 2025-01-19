package com.ftoapanta.pichincha.client_crud.repositories;

import com.ftoapanta.pichincha.client_crud.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPersonId(Long id);

    Page<Client> findAll(Pageable pageable);

    @Query("SELECT c FROM Client c WHERE LOWER(c.person.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Client> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    Optional<Client> findByPersonIdentification(String identification);
}
