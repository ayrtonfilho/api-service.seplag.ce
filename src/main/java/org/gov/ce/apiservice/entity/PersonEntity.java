package org.gov.ce.apiservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "PERSON")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 25)
    private String name;

    @Column(name = "LAST_NAME", length = 25)
    private String lastName;

    @Column(name = "CPF", length = 25)
    private String cpf;

    @Column(name = "FK_STATUS", length = 25)
    private String status;

    @Column(name = "DATE_REGISTER", length = 25)
    private String dateRegister;
}
