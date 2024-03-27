package org.gov.ce.apiservice.controller;

import org.gov.ce.apiservice.entity.MessagePatternEntity;
import org.gov.ce.apiservice.entity.PersonEntity;
import org.gov.ce.apiservice.exception.ApplicationException;
import org.gov.ce.apiservice.exception.DuplicatedDataException;
import org.gov.ce.apiservice.exception.InternalServerErrorException;
import org.gov.ce.apiservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{person_id}")
    public ResponseEntity findOnePerson(@PathVariable("person_id") Long id) {
        try {
            Optional<PersonEntity> person = personService.findOnePerson(id);

            if (person.isPresent()) {
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.status(404).body(new MessagePatternEntity("Person not found in the database"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessagePatternEntity("Internal server error: " + e.getMessage()));
        }
    }

    @PostMapping("/persons")
    public ResponseEntity savePerson(@RequestBody PersonEntity person) {
        try {
            PersonEntity savedPerson = personService.savePerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessagePatternEntity("Internal server error: " + e.getMessage()));
        } catch (DuplicatedDataException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessagePatternEntity("Person with name " + person.getName() + " already exists"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity findAllPersons() {
        try {
            return new ResponseEntity<>(personService.findAllPersons(), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(
                    new MessagePatternEntity("Internal server error\n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
