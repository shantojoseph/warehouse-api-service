package com.vios.enterprise.warehouse.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum DeviceStatus {

    WAITING("WAITING"), READY("READY");

    private String value;

    DeviceStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DeviceStatus fromValue(String value) {

        for (DeviceStatus b : DeviceStatus.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }

        return null;
    }

    @JsonValue
    public String getValue() {

        return value;
    }


}
