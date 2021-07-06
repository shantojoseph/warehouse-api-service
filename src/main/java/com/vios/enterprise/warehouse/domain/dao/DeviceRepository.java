package com.vios.enterprise.warehouse.domain.dao;

import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, String> {

}
