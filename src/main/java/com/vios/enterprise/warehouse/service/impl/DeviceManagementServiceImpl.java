package com.vios.enterprise.warehouse.service.impl;

import com.vios.enterprise.warehouse.domain.dao.DeviceRepository;
import com.vios.enterprise.warehouse.domain.dao.DeviceSearchDao;
import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import com.vios.enterprise.warehouse.model.request.DeviceRequest;
import com.vios.enterprise.warehouse.model.response.Device;
import com.vios.enterprise.warehouse.service.DeviceManagementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DeviceManagementServiceImpl implements DeviceManagementService {

    @Autowired
    DeviceSearchDao deviceSearchDao;

    @Autowired

    DeviceRepository deviceRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Device> getDevices(String status) {

        log.info("Device search  invoked withe status {}", status);

        List<DeviceEntity> deviceEntities = deviceSearchDao.getDevices(status);

        return deviceEntities.stream().map(this::convertToDevice).collect(Collectors.toList());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Device> getDevicesByStatus(String status) {

        log.info("DeviceFor Sale  search  invoked ");

        List<DeviceEntity> deviceEntities = deviceSearchDao.getDevicesByStatus(status);

        return deviceEntities.stream().map(this::convertToDevice).collect(Collectors.toList());
    }

    private Device convertToDevice(DeviceEntity deviceEntity) {
        Device device = new Device();
        device.setId(deviceEntity.getId());
        device.setStatus(deviceEntity.getStatus());
        device.setTemperature(deviceEntity.getTemperature());
        device.setCreatedBy(deviceEntity.getCreatedBy());
        device.setCreatedDate(deviceEntity.getCreatedDate());
        return device;
    }

    @Override
    public void addDevice(DeviceRequest deviceRequest) {

        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(deviceRequest, deviceEntity);
        deviceEntity.setStatus(deviceRequest.getStatus().name());
        deviceEntity.setCreatedBy(deviceRequest.getUserId());
        deviceEntity.setCreatedDate(LocalDate.now());
        deviceEntity.setNew(true);
        log.info(deviceEntity.getId());
        deviceRepository.saveAndFlush(deviceEntity);
    }

    @Override
    public void UpdateDevice(DeviceRequest deviceRequest) {

        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(deviceRequest, deviceEntity);
        deviceEntity.setStatus(deviceRequest.getStatus().name());
        deviceEntity.setCreatedBy(deviceRequest.getUserId());
        deviceEntity.setCreatedDate(LocalDate.now());
        deviceEntity.setNew(false);
        deviceRepository.save(deviceEntity);

    }

}
