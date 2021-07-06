package com.vios.enterprise.warehouse.controller;

import com.vios.enterprise.warehouse.model.request.DeviceRequest;
import com.vios.enterprise.warehouse.model.response.Device;
import com.vios.enterprise.warehouse.model.response.Error;
import com.vios.enterprise.warehouse.model.response.Success;
import com.vios.enterprise.warehouse.service.DeviceConfigurationService;
import com.vios.enterprise.warehouse.service.DeviceManagementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.vios.enterprise.warehouse.constants.ErrorList.*;
import static com.vios.enterprise.warehouse.controller.ExceptionHelper.getErrorResponseEntity;

@RestController
@CrossOrigin
@RequestMapping(value = "/warehouse")
@Log4j2
public class DeviceManagementController {

    @Autowired
    DeviceManagementService deviceManagementService;

    @Autowired

    DeviceConfigurationService deviceConfigurationService;

    @GetMapping(value = "/devices", produces = "application/json")
    public ResponseEntity<Object> getDeviceList(@RequestHeader(value = "Correlation-Id", required = true) @RequestParam String status) {
        try {

            log.info("Device search call started");

            List<Device> response = deviceManagementService.getDevices(status);

            log.info("Device search call successful ");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception exception) {

            log.error("Exception occurred while calling GetDeviceList {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(DE001.name());
            response.setDescription(DE001.getValue());
            response.setRecoverable(false);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping(value = "/devices/{status}", produces = "application/json")
    public ResponseEntity<Object> getReadyForSaleDevices(@PathVariable("status") String status) {
        try {

            log.info("Device search call for Ready for sale started");

            List<Device> response = deviceManagementService.getDevicesByStatus(status);

            log.info("Device search call for Ready for sale successful ");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception exception) {

            log.error("Exception occurred while calling getDeviceForSale {}", exception.getStackTrace());
            Error response = new Error();
            response.setReasonCode(DE002.name());
            response.setDescription(DE002.getValue());
            response.setRecoverable(false);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping(value = "/devices", produces = "application/json")
    public ResponseEntity<Object> addDevice(@RequestHeader(value = "Correlation-Id", required = true) String correlationId, @Valid @NotNull @RequestBody DeviceRequest deviceRequest) {

        try {
            deviceManagementService.addDevice(deviceRequest);

            Success response = new Success();
            response.setResponseCode(DE006.name());
            response.setDescription(DE006.getValue());

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {

            log.error("Exception occurred while calling adding  Device {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
            response.setDescription(exception.getMessage());
            response.setRecoverable(false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping(value = "/devices", produces = "application/json")
    public ResponseEntity<Object> updateDevice(@RequestHeader(value = "Correlation-Id", required = true) String correlationId, @Valid @NotNull @RequestBody DeviceRequest deviceRequest) {

        try {
            deviceManagementService.UpdateDevice(deviceRequest);

            Success response = new Success();
            response.setResponseCode(DE008.name());
            response.setDescription(DE008.getValue());

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {

            log.error("Exception occurred while calling adding  Device {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
            response.setDescription(exception.getMessage());
            response.setRecoverable(false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Error> handleBadRequestException(ServerWebInputException exception) {

        Error response = new Error();

        response.setReasonCode(HttpStatus.BAD_REQUEST.name());
        response.setDescription(exception.getReason());
        response.setRecoverable(false);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Error> handleBadRequestException(WebExchangeBindException exception) {

        return getErrorResponseEntity(exception);
    }

}

