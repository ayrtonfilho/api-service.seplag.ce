package org.gov.ce.apiservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.gov.ce.apiservice.entity.AuthEntity;
import org.gov.ce.apiservice.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class AuthService {
    @Value("${secret.jwt}")
    private String secret;

    public AuthEntity makeLogin(String idApplication) throws Exception {
        try {
            if (Objects.equals(idApplication, "front-end.service-api")) {
                return new AuthEntity(this.generateToken(idApplication));
            }
        } catch (Exception error) {
            throw new ApplicationException("Invalid application request. Please try again.");
        }

        return null;
    }

    private String generateToken(String idApplication) {

        return Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(idApplication)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512,
                        secret.getBytes()
                )
                .compact();
    }
}
