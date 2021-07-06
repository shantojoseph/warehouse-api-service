package com.vios.enterprise.warehouse.domain.dao.impl;

import com.vios.enterprise.warehouse.domain.dao.DeviceSearchDao;
import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import com.vios.enterprise.warehouse.domain.mapper.DeviceEntityMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vios.enterprise.warehouse.constants.SqlQueryConstants.GET_PENDING_ACTIVATION_DEVICE;
import static com.vios.enterprise.warehouse.constants.SqlQueryConstants.GET_READY_FOR_SALE_DEVICE;

@Service
@Log4j2
public class DeviceSearchDaoImpl implements DeviceSearchDao {


    private static final String MAX_TEMPERATURE = "85";
    private static final String MIN_TEMPERATURE = "-25";
    @Autowired
    private DataSource dataSource;

    @Override
    public List<DeviceEntity> getDevices(String status) {

        log.info("Device search Dao invoked withe status {}", status);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        Map<String, String> params = new HashMap<>();
        params.put("status", status);

        return namedParameterJdbcTemplate.query(GET_PENDING_ACTIVATION_DEVICE, params, new DeviceEntityMapper());
    }

    @Override
    public List<DeviceEntity> getDevicesByStatus(String status) {

        try {

            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

            Map<String, String> params = new HashMap<>();
            params.put("status",status);

            log.debug("Device For Sale search  invoked with {}", params.toString());

            return namedParameterJdbcTemplate.query(GET_READY_FOR_SALE_DEVICE, params, new DeviceEntityMapper());

        } catch (DataAccessException dataAccessException) {

            log.error("Exception occurred while retrieving the device list for same");

            throw dataAccessException;
        }
    }
}
