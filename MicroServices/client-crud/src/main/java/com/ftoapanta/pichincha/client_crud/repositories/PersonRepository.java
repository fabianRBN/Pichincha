package com.ftoapanta.pichincha.client_crud.repositories;

import com.ftoapanta.pichincha.client_crud.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByIdentification(String identification);
}
