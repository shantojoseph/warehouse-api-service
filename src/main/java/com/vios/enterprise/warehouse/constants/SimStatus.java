package com.vios.enterprise.warehouse.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SimStatus {
    ACTIVE("ACTIVE"), WAITING_FOR_ACTIVATION("WAITING_FOR_ACTIVATION"), BLOCKED("BLOCKED"), DEACTIVATED("DEACTIVATED");


    private String value;

    SimStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SimStatus fromValue(String value) {

        for (SimStatus b : SimStatus.values()) {
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
