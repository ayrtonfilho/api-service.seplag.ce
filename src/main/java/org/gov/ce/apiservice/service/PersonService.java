package org.gov.ce.apiservice.service;

import org.gov.ce.apiservice.entity.PersonEntity;
import org.gov.ce.apiservice.exception.ApplicationException;
import org.gov.ce.apiservice.exception.DuplicatedDataException;
import org.gov.ce.apiservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<PersonEntity> findOnePerson(Long id) {
        return personRepository.findById(id);
    }

    public PersonEntity savePerson(PersonEntity person) throws Exception {
        Optional<PersonEntity> existingPerson = this.findOnePerson(person.getId());

        if (existingPerson.isEmpty()) {
            return personRepository.save(person);
        } else {
            throw new Exception("Person with name " + person.getName() + " already exists");
        }
    }

    public List<PersonEntity> findAllPersons() throws Exception {
        try {
            return personRepository.findAllPersons();
        } catch (Exception e) {
            throw new Exception("Error occurred while retrieving all persons: " + e.getMessage());
        }
    }
}