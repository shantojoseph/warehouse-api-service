package com.vios.enterprise.warehouse.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtil {

    private final String BASE_PATH = "src/main/resources/DATA/";


    public List<String> getRecordList(String tableName) throws IOException {

        List<Path> listOfDataPath = getPath(BASE_PATH + tableName);

        List<String> listRecord = new ArrayList<>();

        listOfDataPath.forEach(p -> {
            try {
                BufferedReader reader = Files.newBufferedReader(p);
                String line;
                while ((line = reader.readLine()) != null) {
                    listRecord.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
        return listRecord;
    }

    private List<Path> getPath(String filePath) throws IOException {
        return Files.walk(Paths.get(filePath).toAbsolutePath()).
                filter(Files::isRegularFile).filter(t -> t.toString()
                .toLowerCase().endsWith(".data"))
                .collect(Collectors.toList());
    }
}
