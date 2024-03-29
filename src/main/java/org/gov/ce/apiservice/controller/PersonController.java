package org.gov.ce.apiservice.controller;

import jakarta.validation.Valid;
import org.gov.ce.apiservice.dto.PersonDTO;
import org.gov.ce.apiservice.dto.PersonUpdateDTO;
import org.gov.ce.apiservice.entity.ErrorPatternEntity;
import org.gov.ce.apiservice.entity.PersonEntity;
import org.gov.ce.apiservice.exception.DuplicatedDataException;
import org.gov.ce.apiservice.exception.NotFoundException;
import org.gov.ce.apiservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{person_id}")
    public ResponseEntity findOnePersonById(@PathVariable("person_id") Long id) {
        try {
            Optional<PersonEntity> person = personService.findOnePersonById(id);

            return ResponseEntity.ok(person);

        } catch (NotFoundException error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorPatternEntity(HttpStatus.NOT_FOUND, error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, error.getLocalizedMessage(), error.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity findAllPersons() {
        try {
            Optional<List<PersonEntity>> persons = personService.findAllPersons();

            if (persons.isEmpty()) {
                return ResponseEntity
                        .internalServerError()
                        .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Don't exists persons in database!", null));
            }

            return ResponseEntity.status(HttpStatus.OK).body(persons);
        } catch (Exception error) {
            error.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while searching all persons", error.getMessage()));
        }
    }
    @GetMapping("/status/{status_id}")
    public ResponseEntity findOnePersonByStatusId(@PathVariable("status_id") Long statusId) {
        try {
            List<PersonEntity> person = personService.findAllPersonsByStatusId(statusId);

            return ResponseEntity.ok(person);
        } catch (NotFoundException error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorPatternEntity(HttpStatus.NOT_FOUND, error.getMessage(), null));
        } catch (Exception error) {
            error.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Not found results to searching", error.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity savePerson(@RequestBody @Valid PersonDTO person) {
        try {
            PersonEntity savedPerson = personService.savePerson(person);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (DuplicatedDataException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorPatternEntity(HttpStatus.CONFLICT, "Person already exists in database!", error.getMessage()));
        } catch (Exception error) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", error.getMessage()));
        }
    }

    @PutMapping(path = "/update/{person_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePerson(@PathVariable("person_id") Long personId, @RequestBody @Valid PersonUpdateDTO person) {
        try {
            PersonEntity savedPerson = personService.updatePerson(personId, person);

            return ResponseEntity.status(HttpStatus.OK).body(savedPerson);
        } catch (NotFoundException error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorPatternEntity(HttpStatus.NOT_FOUND, "Person don't exists in database!", error.getMessage()));
        } catch (Exception error) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", error.getMessage()));
        }
    }

    @DeleteMapping("/remove/{person_id}")
    public ResponseEntity findOneAndRemove(@PathVariable("person_id") Long id) {
        try {
            return ResponseEntity.ok().body(Map.of("deleted", personService.findOneAndRemove(id), "message", "Person deleted successfully!"));
        } catch (NotFoundException error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorPatternEntity(HttpStatus.NOT_FOUND, error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ErrorPatternEntity(HttpStatus.INTERNAL_SERVER_ERROR, error.getLocalizedMessage(), error.getMessage()));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        for (FieldError fieldError : result.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage());
        }

        return ResponseEntity
                .badRequest()
                .body(new ErrorPatternEntity(HttpStatus.BAD_REQUEST, errorMessage.toString(), ex.getMessage()));
    }
}
