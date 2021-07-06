package com.vios.enterprise.warehouse.model.request;

import com.vios.enterprise.warehouse.constants.DeviceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DeviceRequest implements Serializable {

    @NotNull(message = "Must be present")
    @Length(min = 36, max = 36, message = "Invalid value for the Field ")
    private String id;


    @NotNull(message = "Must be present")
    private DeviceStatus status;

    @NotNull(message = "Must be present")
    @Length(min = 1, max = 4, message = "Invalid value for the Field ")
    private String temperature;


    @Length(min = 3, max = 100, message = "Invalid value for the Field ")
    @NotNull(message = "Must be present")
    private String userId;

}
