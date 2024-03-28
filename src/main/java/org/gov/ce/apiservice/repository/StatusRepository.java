package org.gov.ce.apiservice.repository;

import org.gov.ce.apiservice.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    Optional<StatusEntity> findById(Long id);

    Optional<StatusEntity> findByDescription(StatusEntity.StatusDescription description);
}
