package com.vios.enterprise.warehouse.controller;

import com.vios.enterprise.warehouse.model.request.SIMRequest;
import com.vios.enterprise.warehouse.model.response.Error;
import com.vios.enterprise.warehouse.model.response.Success;
import com.vios.enterprise.warehouse.service.SimManagementService;
import com.vios.enterprise.warehouse.util.DataLoadFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.vios.enterprise.warehouse.constants.ErrorList.*;
import static com.vios.enterprise.warehouse.controller.ExceptionHelper.getErrorResponseEntity;

@RestController
@RequestMapping(value = "/warehouse")
@Log4j2
@CrossOrigin
public class SimManagementController {

    @Autowired
    DataLoadFacade dataLoadFacade;
    @Autowired
    private SimManagementService simManagementService;


    @PostMapping(value = "/sim", produces = "application/json")
    public ResponseEntity<Object> addSim(@RequestHeader(value = "Correlation-Id", required = true) String correlationId, @Valid @NotNull @RequestBody SIMRequest SIMRequest) {

        try {
            simManagementService.addSim(SIMRequest);

            Success response = new Success();
            response.setResponseCode(DE007.name());
            response.setDescription(DE007.getValue());

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {

            log.error("Exception occurred while calling adding  SIM {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
            response.setDescription(DE004.getValue());
            response.setRecoverable(false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping(value = "/sim", produces = "application/json")
    public ResponseEntity<Object> UpdateSim(@RequestHeader(value = "Correlation-Id", required = true) String correlationId, @Valid @NotNull @RequestBody SIMRequest SIMRequest) {

        try {
            simManagementService.updateSim(SIMRequest);

            Success response = new Success();
            response.setResponseCode(DE009.name());
            response.setDescription(DE009.getValue());

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {

            log.error("Exception occurred while updating  SIM {}", exception.getStackTrace());

            Error response = new Error();
            response.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
            response.setDescription(DE004.getValue());
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
