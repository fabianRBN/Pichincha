package com.ftoapanta.pichincha.client_crud.controllers;


import com.ftoapanta.pichincha.client_crud.entities.Person;
import com.ftoapanta.pichincha.client_crud.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "person")
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> readById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.readById(id));
    }

    @GetMapping
    public ResponseEntity<List<Person>> readAll() {
        return ResponseEntity.ok(personService.readAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(personService.update(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
