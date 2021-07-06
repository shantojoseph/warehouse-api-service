package com.vios.enterprise.warehouse.domain.dao;

import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceSearchDao {
    List<DeviceEntity> getDevices(String status);

    List<DeviceEntity> getDevicesByStatus(String status);

}
