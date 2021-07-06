package com.vios.enterprise.warehouse.domain.mapper;

import com.vios.enterprise.warehouse.domain.entity.DeviceEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceEntityMapper implements RowMapper {

    @Override
    public DeviceEntity mapRow(ResultSet resultSet, int i) throws SQLException {

        DeviceEntity deviceEntity = new DeviceEntity();

        deviceEntity.setId(resultSet.getString("id"));
        deviceEntity.setTemperature(resultSet.getString("Temperature"));
        deviceEntity.setStatus(resultSet.getString("status"));
        deviceEntity.setCreatedDate(resultSet.getDate("Created_Date").toLocalDate());
        deviceEntity.setCreatedBy(resultSet.getString("Created_By"));
        return deviceEntity;
    }
}
