package com.vios.enterprise.warehouse;

import com.vios.enterprise.warehouse.constants.DeviceStatus;
import com.vios.enterprise.warehouse.constants.SimStatus;
import com.vios.enterprise.warehouse.model.exception.DeviceNotfoundException;
import com.vios.enterprise.warehouse.model.exception.SimNotActivatedException;
import com.vios.enterprise.warehouse.model.exception.SimNotfoundException;
import com.vios.enterprise.warehouse.model.request.DeviceConfigurationRequest;
import com.vios.enterprise.warehouse.model.request.DeviceRequest;
import com.vios.enterprise.warehouse.model.request.SIMRequest;
import com.vios.enterprise.warehouse.service.DeviceConfigurationService;
import com.vios.enterprise.warehouse.service.DeviceManagementService;
import com.vios.enterprise.warehouse.service.SimManagementService;
import com.vios.enterprise.warehouse.util.DataLoadFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.UUID;

@SpringBootTest
class WarehouseServiceApplicationTests {

    @Autowired
    DeviceManagementService deviceManagementService;

    @Autowired
    SimManagementService simManagementService;

    @Autowired
    DeviceConfigurationService deviceConfigurationService;

    @Autowired
    DataLoadFacade dataLoadFacade;

    @Test
    void contextLoads() {
    }

    @Test
    public void addDevice() {

        DeviceRequest deviceRequest = new DeviceRequest();
        deviceRequest.setId(UUID.randomUUID().toString());
        deviceRequest.setStatus(DeviceStatus.WAITING);
        deviceRequest.setTemperature("15");
        deviceRequest.setUserId("Admin");

        deviceManagementService.addDevice(deviceRequest);


    }

    @Test
    public void addSIM() {

        SIMRequest SIMRequest = new SIMRequest();
        SIMRequest.setId(UUID.randomUUID().toString());
        SIMRequest.setStatus(SimStatus.WAITING_FOR_ACTIVATION);
        SIMRequest.setUserId("Admin");
        simManagementService.addSim(SIMRequest);

    }

    @Test
    public void configureThrowsSimNotActivated() {

        DeviceRequest deviceRequest = new DeviceRequest();
        deviceRequest.setId(UUID.randomUUID().toString());
        deviceRequest.setStatus(DeviceStatus.WAITING);
        deviceRequest.setTemperature("15");
        deviceRequest.setUserId("Admin");

        deviceManagementService.addDevice(deviceRequest);


        SIMRequest SIMRequest = new SIMRequest();
        SIMRequest.setId(UUID.randomUUID().toString());
        SIMRequest.setStatus(SimStatus.WAITING_FOR_ACTIVATION);
        SIMRequest.setUserId("Admin");
        simManagementService.addSim(SIMRequest);

        DeviceConfigurationRequest deviceConfigurationRequest = new DeviceConfigurationRequest();

        deviceConfigurationRequest.setUserId("Admin");
        deviceConfigurationRequest.setDeviceId(SIMRequest.getId());
        deviceConfigurationRequest.setSimId(SIMRequest.getId());
        deviceConfigurationRequest.setStatus(DeviceStatus.READY);


        Assertions.assertThrows(SimNotActivatedException.class, () -> {
            deviceConfigurationService.configure(deviceConfigurationRequest);
        });
    }


    @Test
    public void configureThrowsSimNotFound() {

        DeviceConfigurationRequest deviceConfigurationRequest = new DeviceConfigurationRequest();

        deviceConfigurationRequest.setUserId("Admin");
        deviceConfigurationRequest.setDeviceId(UUID.randomUUID().toString());
        deviceConfigurationRequest.setSimId(UUID.randomUUID().toString());
        deviceConfigurationRequest.setStatus(DeviceStatus.READY);


        Assertions.assertThrows(SimNotfoundException.class, () -> {
            deviceConfigurationService.configure(deviceConfigurationRequest);
        });
    }

    @Test
    public void loadDevice() throws IOException {
        dataLoadFacade.load("DEVICE");
    }

    @Test
    public void loadSIM() throws IOException {
        dataLoadFacade.load("SIM");
    }

    @Test
    public void loadMapping() throws IOException {
        dataLoadFacade.load("DEVICE_SIM_MAPPING");
    }

    @Test
    public void configureThrowsDeviceNotFound() {


        SIMRequest SIMRequest = new SIMRequest();
        SIMRequest.setId(UUID.randomUUID().toString());
        SIMRequest.setStatus(SimStatus.ACTIVE);
        SIMRequest.setUserId("Admin");

        simManagementService.addSim(SIMRequest);

        DeviceConfigurationRequest deviceConfigurationRequest = new DeviceConfigurationRequest();

        deviceConfigurationRequest.setUserId("Admin");
        deviceConfigurationRequest.setDeviceId(UUID.randomUUID().toString());
        deviceConfigurationRequest.setSimId(SIMRequest.getId());
        deviceConfigurationRequest.setStatus(DeviceStatus.READY);


        Assertions.assertThrows(DeviceNotfoundException.class, () -> {
            deviceConfigurationService.configure(deviceConfigurationRequest);
        });
    }
}
