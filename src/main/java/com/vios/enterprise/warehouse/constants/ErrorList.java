package com.vios.enterprise.warehouse.constants;

import lombok.Getter;

@Getter
public enum ErrorList {

    DE001("Exception occurred while getting devices"),
    DE002("Exception occurred while getting available devices"),
    DE000("Device configured successfully"),
    DE004("Internal service Exception"),
    DE005("Data load completed successfully"),
    DE006("Device Added successfully"),
    DE008("Device Updated successfully"),
    DE007("SIM Added successfully"),
    DE009("SIM Updated successfully");


    private final String value;

    ErrorList(String value) {
        this.value = value;
    }


}
