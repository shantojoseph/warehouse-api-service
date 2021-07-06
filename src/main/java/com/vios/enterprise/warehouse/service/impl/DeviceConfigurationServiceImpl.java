package com.vios.enterprise.warehouse.service.impl;

import com.vios.enterprise.warehouse.constants.DeviceStatus;
import com.vios.enterprise.warehouse.constants.SimStatus;
import com.vios.enterprise.warehouse.domain.dao.DeviceRepository;
import com.vios.enterprise.warehouse.domain.dao.DeviceSimMappingRepository;
import com.vios.enterprise.warehouse.domain.dao.SimRepository;
import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import com.vios.enterprise.warehouse.domain.entity.DeviceSimMappingEntity;
import com.vios.enterprise.warehouse.domain.entity.SimEntity;
import com.vios.enterprise.warehouse.model.exception.DeviceNotfoundException;
import com.vios.enterprise.warehouse.model.exception.SimNotActivatedException;
import com.vios.enterprise.warehouse.model.exception.SimNotfoundException;
import com.vios.enterprise.warehouse.model.exception.SystemException;
import com.vios.enterprise.warehouse.model.request.DeviceConfigurationRequest;
import com.vios.enterprise.warehouse.service.DeviceConfigurationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class DeviceConfigurationServiceImpl implements DeviceConfigurationService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceSimMappingRepository deviceSimMappingRepository;

    @Autowired
    SimRepository simRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void configure(DeviceConfigurationRequest deviceConfigurationRequest) throws SimNotfoundException, DeviceNotfoundException, SimNotActivatedException, SystemException {


        Optional<SimEntity> simEntityOptional = simRepository.findById(deviceConfigurationRequest.getSimId());

        simEntityOptional.orElseThrow(() -> new SimNotfoundException(" SIM Id not found please use a valid sim id to configure"));

        SimEntity simEntity = simEntityOptional.get();

        if (!(simEntity.getStatus().equals(SimStatus.ACTIVE.name())) && deviceConfigurationRequest.getStatus().name().equals(DeviceStatus.READY.name())) {

            throw new SimNotActivatedException("SIM should be active to mark the device Ready. Please activate the SIM first");

        }

        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(deviceConfigurationRequest.getDeviceId());

        deviceEntityOptional.orElseThrow(() -> new DeviceNotfoundException("Device Id not found . Please use valid device Id to configure"));

        try {

            DeviceSimMappingEntity deviceSimMappingEntity = new DeviceSimMappingEntity();
            deviceSimMappingEntity.setDeviceId(deviceConfigurationRequest.getDeviceId());
            deviceSimMappingEntity.setId(UUID.randomUUID().toString());
            deviceSimMappingEntity.setCreatedBy(deviceConfigurationRequest.getUserId());
            deviceSimMappingEntity.setCreatedDate(LocalDate.now());
            deviceSimMappingEntity.setSIMId(deviceConfigurationRequest.getSimId());

            deviceSimMappingRepository.save(deviceSimMappingEntity);

            DeviceEntity deviceEntity = deviceEntityOptional.get();

            deviceEntity.setStatus(deviceConfigurationRequest.getStatus().name());
            deviceEntity.setModifiedBy(deviceConfigurationRequest.getUserId());
            deviceEntity.setModifiedDate(LocalDate.now());
            deviceRepository.save(deviceEntity);

        } catch (Exception e) {
            log.error("Error while saving to DB {} ", e.getStackTrace());
            throw new SystemException("Error while saving to DB");

        }
    }


}
