package com.vios.enterprise.warehouse.util;

import com.vios.enterprise.warehouse.domain.dao.DeviceSimMappingRepository;
import com.vios.enterprise.warehouse.domain.entity.DeviceSimMappingEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class DeviceSimMappingDataLoadServiceImpl implements DataLoadService {

    @Autowired
    private DeviceSimMappingRepository deviceSimMappingRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void load(List<String> records) {

        log.info("Data loading service started for device sim mappings");

        try {
            List<DeviceSimMappingEntity> deviceSimMappingEntityList = new ArrayList<>();
            records.forEach(
                    record ->
                    {
                        DeviceSimMappingEntity deviceSimMappingEntity = new DeviceSimMappingEntity();
                        String[] data = record.split(",");
                        deviceSimMappingEntity.setId(data[0]);
                        deviceSimMappingEntity.setDeviceId(data[1]);
                        deviceSimMappingEntity.setSIMId(data[2]);
                        deviceSimMappingEntity.setCreatedBy("Admin");
                        deviceSimMappingEntity.setCreatedDate(LocalDate.now(ZoneOffset.UTC));
                        deviceSimMappingEntityList.add(deviceSimMappingEntity);
                    }
            );
            deviceSimMappingRepository.saveAll(deviceSimMappingEntityList);

            log.info("Data loading service completed for device sim mappings");

        } catch (Exception datLoadException) {
            log.error("Exception occured while saving Data to DB ");
            throw datLoadException;
        }


    }

    @Override
    public String getType() {
        return Tables.DEVICE_SIM_MAPPING.name();
    }
}
