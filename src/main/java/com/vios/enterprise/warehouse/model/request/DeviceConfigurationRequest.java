package com.vios.enterprise.warehouse.model.request;

import com.vios.enterprise.warehouse.constants.DeviceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DeviceConfigurationRequest implements Serializable {

    @NotNull(message = "Must be present")
    private String simId;

    @NotNull(message = "Must be present")
    private String deviceId;

    @NotNull(message = "Must be present")
    private DeviceStatus status;

    @NotNull(message = "Must be present")
    private String userId;
}
