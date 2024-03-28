package org.gov.ce.apiservice.service;

import jakarta.annotation.PostConstruct;
import org.gov.ce.apiservice.entity.StatusEntity;
import org.gov.ce.apiservice.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    @PostConstruct
    public void init() {
        createStatus("ATIVO");
        createStatus("INATIVO");
    }

    private void createStatus(String statusValue) {
        if (statusRepository.findByDescription(StatusEntity.StatusDescription.valueOf(statusValue)).isEmpty()) {
            StatusEntity statusEntity = new StatusEntity();
            statusEntity.setDescription(StatusEntity.StatusDescription.valueOf(statusValue));

            statusRepository.save(statusEntity);
        }
    }
}
