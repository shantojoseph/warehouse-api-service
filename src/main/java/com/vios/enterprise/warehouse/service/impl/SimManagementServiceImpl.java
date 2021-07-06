package com.vios.enterprise.warehouse.service.impl;

import com.vios.enterprise.warehouse.domain.dao.SimRepository;
import com.vios.enterprise.warehouse.domain.entity.SimEntity;
import com.vios.enterprise.warehouse.model.request.SIMRequest;
import com.vios.enterprise.warehouse.service.SimManagementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Log4j2
public class SimManagementServiceImpl implements SimManagementService {

    @Autowired
    SimRepository simRepository;

    @Override
    public void addSim(SIMRequest SIMRequest) {
        SimEntity simEntity = new SimEntity();
        BeanUtils.copyProperties(SIMRequest, simEntity);
        simEntity.setNew(true);
        simEntity.setStatus(SIMRequest.getStatus().name());
        simEntity.setCreatedBy(SIMRequest.getUserId());
        simEntity.setCreatedDate(LocalDate.now());

        simRepository.save(simEntity);
    }

    @Override
    public void updateSim(SIMRequest SIMRequest) {

        SimEntity simEntity = new SimEntity();
        BeanUtils.copyProperties(SIMRequest, simEntity);
        simEntity.setNew(false);
        simEntity.setStatus(SIMRequest.getStatus().name());
        simEntity.setModifiedBy(SIMRequest.getUserId());
        simEntity.setModifiedDate(LocalDate.now());
        simRepository.save(simEntity);

    }
}
