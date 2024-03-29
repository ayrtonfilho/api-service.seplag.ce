package org.gov.ce.apiservice.repository;

import org.gov.ce.apiservice.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(value = "SELECT p FROM TB_PERSON p WHERE p.id = ?1")
    Optional<PersonEntity> findById(Long id);

    Optional<PersonEntity> findByName(String name);

    Optional<PersonEntity> findByCpf(String cpf);

    @Query("SELECT p FROM TB_PERSON p WHERE p.status.id = ?1 ORDER BY p.name ASC")
    ArrayList<PersonEntity> findByStatusId(Long statusId);

    @Modifying
    @Transactional
    @Query("UPDATE TB_PERSON p SET p.name = ?1, p.lastName = ?2, p.status.id = ?3 WHERE p.id = ?4")
    void updatePersonById(String name, String lastName, Long newStatusId, Long id);
}