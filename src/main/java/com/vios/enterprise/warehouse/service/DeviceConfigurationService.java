package com.vios.enterprise.warehouse.service;

import com.vios.enterprise.warehouse.model.exception.*;
import com.vios.enterprise.warehouse.model.request.DeviceConfigurationRequest;
import org.springframework.stereotype.Component;

@Component
public interface DeviceConfigurationService {
    void configure(DeviceConfigurationRequest deviceConfigurationRequest) throws DeviceSimNotfoundException, SimNotfoundException, DeviceNotfoundException, SimNotActivatedException, SystemException;
}
