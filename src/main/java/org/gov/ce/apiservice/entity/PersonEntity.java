package org.gov.ce.apiservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_PERSON")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "LAST_NAME", length = 30, nullable = false)
    private String lastName;

    @Column(name = "CPF", length = 25, nullable = false, unique = true)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "FK_STATUS", nullable = false)
    private StatusEntity status;

    @Column(name = "DATE_REGISTER", length = 40, nullable = false)
    private Date dateRegister;

    @Override
    public String toString() {
        return "{" +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cpf='" + cpf + '\'' +
                ", status='" + status + '\'' +
                "}";
    }
}
