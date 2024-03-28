package org.gov.ce.apiservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorPatternEntity {
    private HttpStatus status;
    private String message;
    private String cause;

    @Override
    public String toString() {
        return "{" +
                "status=" + status.value() +
                ", message='" + message + '\'' +
                ", cause='" + cause + '\'' +
                "}";
    }

}
