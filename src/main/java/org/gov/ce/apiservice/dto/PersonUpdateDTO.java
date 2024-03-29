package org.gov.ce.apiservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDTO {

    @NotBlank(message = "O nome não pode estar em branco.")
    private String name;

    @NotBlank(message= "O sobrenome nao pode estar em branco.")
    private String lastName;

    @NotNull(message = "O status não pode ser nulo")
    @Min(value = 1, message = "O status não pode ser menor que 1")
    @Max(value = 2, message = "O status não pode ser maior que 2")
    @JsonProperty("fk_status")
    private Long status;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fk_status='" + status + '\'' +
                "}";
    }
}
