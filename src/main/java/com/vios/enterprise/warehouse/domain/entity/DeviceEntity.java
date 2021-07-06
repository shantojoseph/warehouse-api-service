package com.vios.enterprise.warehouse.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Component
@Table(name = "DEVICE")
public class DeviceEntity extends AuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    private boolean isNew = true;

    @Id
    private String id;

    @Column(name = "STATUS")
    private String status;


    @Column(name = "temperature")
    private String temperature;

}
