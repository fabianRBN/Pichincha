package com.ftoapanta.pichincha.client_crud.services;

import com.ftoapanta.pichincha.client_crud.entities.Person;
import com.ftoapanta.pichincha.client_crud.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
@AllArgsConstructor //lombok
public class PersonServiceImpl  implements PersonService {
    private final PersonRepository personRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Long id, Person person) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + id));
        existingPerson.setName(person.getName());
        existingPerson.setGender(person.getGender());
        existingPerson.setAge(person.getAge());
        existingPerson.setIdentification(person.getIdentification());
        existingPerson.setAddress(person.getAddress());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        return personRepository.save(existingPerson);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Person readById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + id));
    }

    public List<Person> readAll() {
        return personRepository.findAll();
    }
}
