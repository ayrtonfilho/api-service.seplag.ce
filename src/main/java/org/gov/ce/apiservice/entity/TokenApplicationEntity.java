package org.gov.ce.apiservice.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenApplicationEntity {
    private String token;
    private String applicationId;

    public TokenApplicationEntity(String token, String applicationId) {
        this.token = token;
        this.applicationId = applicationId;
    }

}
