package com.vios.enterprise.warehouse.controller;

import com.vios.enterprise.warehouse.model.exception.DeviceNotfoundException;
import com.vios.enterprise.warehouse.model.exception.DeviceSimNotfoundException;
import com.vios.enterprise.warehouse.model.exception.SimNotActivatedException;
import com.vios.enterprise.warehouse.model.exception.SimNotfoundException;
import com.vios.enterprise.warehouse.model.request.DeviceConfigurationRequest;
import com.vios.enterprise.warehouse.model.response.Error;
import com.vios.enterprise.warehouse.model.response.Success;
import com.vios.enterprise.warehouse.service.DeviceConfigurationService;
import com.vios.enterprise.warehouse.util.DataLoadFacade;
import com.vios.enterprise.warehouse.util.Tables;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import static com.vios.enterprise.warehouse.constants.ErrorList.*;
import static com.vios.enterprise.warehouse.controller.ExceptionHelper.getErrorResponseEntity;

@RestController
@RequestMapping(value = "/warehouse")
@Log4j2
@CrossOrigin
public class DeviceConfigurationController {

    @Autowired
    DataLoadFacade dataLoadFacade;

    @Autowired
    private DeviceConfigurationService deviceConfigurationService;

    @PostMapping(value = "/devices/configure", produces = "application/json")
    public ResponseEntity<Object> configure(@RequestHeader(value = "Correlation-Id", required = true) String
                                                    correlationId, @Valid @NotNull @RequestBody DeviceConfigurationRequest deviceConfigurationRequest) {
        try {
            log.info(" Device configuration  API invoked with: {}", deviceConfigurationRequest);

            deviceConfigurationService.configure(deviceConfigurationRequest);

            Success response = new Success();
            response.setResponseCode(DE000.name());
            response.setDescription(DE000.getValue());

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

        } catch (DeviceSimNotfoundException | SimNotfoundException | DeviceNotfoundException | SimNotActivatedException exception) {
            log.error("Exception occurred while calling configuring Device {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(HttpStatus.UNPROCESSABLE_ENTITY.name());
            response.setDescription(exception.getMessage());
            response.setRecoverable(false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception exception) {

            log.error("Exception occurred while calling configuring Device {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(DE004.name());
            response.setDescription(DE004.getValue());
            response.setRecoverable(false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @PostMapping(value = "/devices/load", produces = "application/json")
    public ResponseEntity<Object> load(@RequestHeader(value = "Correlation-Id", required = true) String correlationId) {
        try {

            log.info("Loading data to DEVICE");
            dataLoadFacade.load(Tables.DEVICE.name());
            log.info("Loading data to SIM");
            dataLoadFacade.load(Tables.SIM.name());
            log.info("Loading data to DEVICE_SIM_MAPPING");
            dataLoadFacade.load(Tables.DEVICE_SIM_MAPPING.name());

            Success response = new Success();
            response.setResponseCode(DE005.name());
            response.setDescription(DE005.getValue());

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

        } catch (IOException exception) {

            log.error("Exception occurred while calling configuring Device {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
            response.setDescription(exception.getMessage());
            response.setRecoverable(false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Error> handleBadRequestException(WebExchangeBindException exception) {

        return getErrorResponseEntity(exception);
    }


}
