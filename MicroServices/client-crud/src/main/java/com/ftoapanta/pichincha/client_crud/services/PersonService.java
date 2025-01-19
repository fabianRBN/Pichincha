package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.entities.Person;

import java.util.List;

public interface PersonService {
    Person create(Person person);

    Person update(Long id, Person person);

    void delete(Long id);

    Person readById(Long id);

    List<Person> readAll();
}
