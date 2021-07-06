package com.vios.enterprise.warehouse.util;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataLoadService {

    void load(List<String> records);

    String getType();

}
