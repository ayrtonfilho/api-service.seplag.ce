package org.gov.ce.apiservice.repository;

import org.gov.ce.apiservice.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Query(value = "SELECT * FROM PERSON WHERE ID = ?", nativeQuery = true)
    public PersonEntity findByPerson(String id);

    @Query(value = "SELECT * FROM PERSON ORDER BY NAME ASC", nativeQuery = true)
    public List<PersonEntity> findAllPersons();
}