package org.gov.ce.apiservice.service;

import org.gov.ce.apiservice.dto.PersonDTO;
import org.gov.ce.apiservice.entity.PersonEntity;
import org.gov.ce.apiservice.entity.StatusEntity;
import org.gov.ce.apiservice.exception.DuplicatedDataException;
import org.gov.ce.apiservice.exception.InternalServerErrorException;
import org.gov.ce.apiservice.exception.NotFoundException;
import org.gov.ce.apiservice.repository.PersonRepository;
import org.gov.ce.apiservice.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Optional<PersonEntity> findOnePersonById(Long id) throws Exception {
        try {
            Optional<PersonEntity> person = personRepository.findById(id);

            if (person.isEmpty()) {
                throw new NotFoundException("User don't exists in database!");
            }

            return person;
        } catch (NotFoundException error) {
            throw new NotFoundException(error.getMessage());
        } catch (Exception error) {
            throw new InternalServerErrorException("Internal Server Error: " + error.getMessage());
        }
    }

    public List<PersonEntity> findAllPersonsByStatusId(Long statusId) throws Exception {
        try {
            List<PersonEntity> person = personRepository.findByStatusId(statusId);
            personRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
            if (person.isEmpty()) {
                return new ArrayList<>();
            }

            return person;
        } catch (Exception error) {
           throw new InternalServerErrorException("Internal Server Error: " + error.getMessage());
        }
    }

    public Optional<PersonEntity> findOnePersonByName(String name) {
        return personRepository.findByName(name);
    }

    public Optional<PersonEntity> findOnePersonByCpf(String cpf) {
        return personRepository.findByCpf(cpf);
    }

    public PersonEntity savePerson(PersonDTO personDTO) throws Exception {
        Optional<PersonEntity> existingPerson = findOnePersonByNameOrCpf(personDTO.getName(), personDTO.getCpf());
        System.out.println(existingPerson.isEmpty());

        if (existingPerson.isPresent()) {
            throw new DuplicatedDataException("Person with name or CPF " + personDTO.getName() + " already exists");
        }

        Long statusId = personDTO.getStatus();

        StatusEntity statusEntity = statusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID: " + statusId));

        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personDTO.getName());
        personEntity.setLastName(personDTO.getLastName());
        personEntity.setCpf(personDTO.getCpf());
        personEntity.setStatus(statusEntity);
        personEntity.setDateRegister(new Date());

        personRepository.save(personEntity);

        return personEntity;
    }

    public Optional<PersonEntity> findOnePersonByNameOrCpf(String name, String cpf) {
        Optional<PersonEntity> byName = this.findOnePersonByName(name);
        return byName.isPresent() ? byName : this.findOnePersonByCpf(cpf);
    }

    public Optional<List<PersonEntity>> findAllPersons() throws Exception {
        try {
            return Optional.of(personRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        } catch (Exception e) {
            throw new InternalServerErrorException("Error occurred while retrieving all persons: " + e.getMessage());
        }
    }
}