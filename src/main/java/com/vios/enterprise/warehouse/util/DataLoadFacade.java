package com.vios.enterprise.warehouse.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
public class DataLoadFacade {

    @Autowired
    Collection<DataLoadService> dataLoadServiceList;

    @Autowired
    Map<String, DataLoadService> dataLoaderServiceMap;

    @Autowired
    FileUtil fileUtil;

    @Transactional(propagation = Propagation.REQUIRED)
    public void load(String tableName) throws IOException {

        try {
            List<String> records = fileUtil.getRecordList(tableName);
            dataLoaderServiceMap.get(tableName).load(records);

        } catch (IOException exception) {
            log.error("Exception occurred while calling load service");
            throw exception;
        }
    }

    @PostConstruct
    public void postConstruct() {
        dataLoaderServiceMap = dataLoadServiceList.stream().collect(
                Collectors.toMap(dataLoadService -> dataLoadService.getType(), dataLoadService -> dataLoadService)
        );
    }

}
