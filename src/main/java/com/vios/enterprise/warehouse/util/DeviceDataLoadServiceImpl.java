package com.vios.enterprise.warehouse.util;

import com.vios.enterprise.warehouse.domain.dao.DeviceRepository;
import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class DeviceDataLoadServiceImpl implements DataLoadService {

    @Autowired
    private DeviceRepository deviceRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void load(List<String> records) {

        log.info("Data loading service started for device");

        try {
            List<DeviceEntity> deviceEntityList = new ArrayList<>();
            records.forEach(
                    record ->
                    {
                        DeviceEntity deviceEntity = new DeviceEntity();
                        String[] data = record.split(",");
                        deviceEntity.setId(data[0]);
                        deviceEntity.setStatus(data[1]);
                        deviceEntity.setTemperature(data[2]);
                        deviceEntity.setCreatedBy("Admin");
                        deviceEntity.setCreatedDate(LocalDate.now(ZoneOffset.UTC));
                        deviceEntityList.add(deviceEntity);
                    }
            );
            deviceRepository.saveAll(deviceEntityList);
        } catch (Exception datLoadException) {
            log.error("Exception occured while saving Data to DB ");
            throw datLoadException;
        }
        log.info("Data loading service completed device");

    }

    @Override
    public String getType() {
        return Tables.DEVICE.name();
    }
}
