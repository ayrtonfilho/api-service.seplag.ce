package org.gov.ce.apiservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessagePatternEntity {
    private String message;

    @Override
    public String toString() {
        return "{" +
                    "message='" + message + '\'' +
                "}";
    }
}
