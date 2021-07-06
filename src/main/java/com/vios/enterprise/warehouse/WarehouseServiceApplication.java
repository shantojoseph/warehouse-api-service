package com.vios.enterprise.warehouse;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@Log4j2
public class WarehouseServiceApplication  {

    public static void main(String[] args) {

        log.info("Starting the Device management application");
        SpringApplication.run(WarehouseServiceApplication.class, args);

    }
}
