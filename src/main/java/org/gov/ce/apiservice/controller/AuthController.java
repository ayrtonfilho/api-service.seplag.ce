package org.gov.ce.apiservice.controller;

import org.gov.ce.apiservice.entity.AuthEntity;
import org.gov.ce.apiservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<String> makeLogin(
            @RequestBody AuthEntity data
    ) {
        try {
            AuthEntity tokens = authService.makeLogin(data.getIdApplication());

            return new ResponseEntity(tokens, HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
