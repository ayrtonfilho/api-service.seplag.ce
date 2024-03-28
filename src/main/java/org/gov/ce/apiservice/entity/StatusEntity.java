package org.gov.ce.apiservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "TB_STATUS")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "DESCRIPTION", length = 10)
    private StatusDescription description;

    public enum StatusDescription {
        ATIVO, INATIVO;
    }
}
