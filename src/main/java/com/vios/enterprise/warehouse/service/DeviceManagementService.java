package com.vios.enterprise.warehouse.service;

import com.vios.enterprise.warehouse.model.request.DeviceRequest;
import com.vios.enterprise.warehouse.model.response.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceManagementService {
    List<Device> getDevices(String status);

    List<Device> getDevicesByStatus(String status);

    void addDevice(DeviceRequest deviceRequest);

    void UpdateDevice(DeviceRequest deviceRequest);


}
