package com.vios.enterprise.warehouse.util;

import com.vios.enterprise.warehouse.domain.dao.SimRepository;
import com.vios.enterprise.warehouse.domain.entity.SimEntity;
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
public class SIMDataLoadServiceImpl implements DataLoadService {

    @Autowired
    private SimRepository simRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void load(List<String> records) {

        log.info("Data loading service started for SIM ");

        try {
            List<SimEntity> simEntityList = new ArrayList<>();

            for (String record : records) {
                SimEntity simEntity = new SimEntity();

                String[] data = record.split(",");

                simEntity.setId(data[0]);
                simEntity.setOperatorCode(data[1]);
                simEntity.setCountry(data[2]);
                simEntity.setStatus(data[3]);
                simEntity.setCreatedBy("Admin");
                simEntity.setCreatedDate(LocalDate.now(ZoneOffset.UTC));
                simEntityList.add(simEntity);
            }

            simRepository.saveAll(simEntityList);

        } catch (Exception datLoadException) {
            log.error("Exception occured while saving Data to DB ");
            throw datLoadException;
        }
        log.info("Data loading service completed for SIM");

    }

    @Override
    public String getType() {
        return Tables.SIM.name();
    }
}
