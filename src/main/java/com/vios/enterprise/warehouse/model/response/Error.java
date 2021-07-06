package com.vios.enterprise.warehouse.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Error {

    private String reasonCode;

    private String description;

    private Boolean recoverable;

}
