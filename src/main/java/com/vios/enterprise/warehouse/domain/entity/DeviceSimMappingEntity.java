package com.vios.enterprise.warehouse.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Component
@Table(name = "device_sim_mapping")
public class DeviceSimMappingEntity extends AuditEntity {
    @Id
    private String id;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "SIM_ID")
    private String sIMId;

}
