package com.vios.enterprise.warehouse.model.request;

import com.vios.enterprise.warehouse.constants.SimStatus;
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
public class SIMRequest implements Serializable {

    @NotNull(message = "Must be present")
    @Length(min = 36, max = 36, message = "Invalid value for the Field ")
    private String id;

    @NotNull(message = "Must be present")
    @Length(min = 2, max = 30, message = "Invalid value for the Field ")
    private String operatorCode;

    @Length(min = 3, max = 3, message = "Invalid value for the Field ")
    @NotNull(message = "Must be present")
    private String country;

    @NotNull(message = "Must be present")
    private SimStatus status;

    @Length(min = 3, max = 100, message = "Invalid value for the Field ")
    @NotNull(message = "Must be present")
    private String userId;
}
