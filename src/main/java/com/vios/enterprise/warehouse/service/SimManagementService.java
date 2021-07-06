package com.vios.enterprise.warehouse.service;

import com.vios.enterprise.warehouse.model.request.SIMRequest;
import org.springframework.stereotype.Service;

@Service
public interface SimManagementService {
    void addSim(SIMRequest SIMRequest);

    void updateSim(SIMRequest SIMRequest);

}
