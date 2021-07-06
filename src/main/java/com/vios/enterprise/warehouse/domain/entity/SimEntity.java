package com.vios.enterprise.warehouse.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Component
@Table(name = "SIM")
public class SimEntity extends AuditEntity {

    @Id
    private String id;

    @Transient
    private boolean isNew = true;

    @Column(name = "Operator_code")
    private String operatorCode;

    @Column(name = "Country")
    private String country;

    @Column(name = "status")
    private String status;

}
