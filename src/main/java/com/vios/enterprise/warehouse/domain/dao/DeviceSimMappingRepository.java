package com.vios.enterprise.warehouse.domain.dao;

import com.vios.enterprise.warehouse.domain.entity.DeviceSimMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceSimMappingRepository extends JpaRepository<DeviceSimMappingEntity, String> {
}
