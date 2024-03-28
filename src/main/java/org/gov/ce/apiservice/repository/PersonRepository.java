package org.gov.ce.apiservice.repository;

import org.gov.ce.apiservice.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(value = "SELECT p FROM TB_PERSON p WHERE p.id = ?1")
    Optional<PersonEntity> findById(Long id);

    Optional<PersonEntity> findByName(String name);

    Optional<PersonEntity> findByCpf(String cpf);

    @Query("SELECT p FROM TB_PERSON p WHERE p.status.id = ?1 ORDER BY p.name ASC")
    ArrayList<PersonEntity> findByStatusId(Long statusId);
}