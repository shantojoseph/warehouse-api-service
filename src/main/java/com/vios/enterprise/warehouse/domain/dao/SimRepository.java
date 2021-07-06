package com.vios.enterprise.warehouse.domain.dao;

import com.vios.enterprise.warehouse.domain.entity.SimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimRepository extends JpaRepository<SimEntity, String> {
}
