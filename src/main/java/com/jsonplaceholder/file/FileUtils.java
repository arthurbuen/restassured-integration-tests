package com.jsonplaceholder.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public static <T> List<T> getObjectsFromJsonFile(String fileName, TypeReference<List<T>> typeReference) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(fileName)));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
