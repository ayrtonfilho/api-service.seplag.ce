package org.gov.ce.apiservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthEntity {

    public AuthEntity() {}
    private String idApplication;

    @Override
    public String toString() {
        return "AuthEntity{" +
                    "id-application='" + idApplication + '\'' +
                '}';
    }
}
