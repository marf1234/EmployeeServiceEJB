package com.innowise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convert(HttpServletRequest request, Class<T> clazz) throws IOException {
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json += line;
        }
        return objectMapper.readValue(json, clazz);
    }

    public static String toJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }
}
//public class JsonConverter {
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public static <T> T convert(HttpServletRequest request, Class<T> clazz) throws IOException {
//        return objectMapper.readValue(request.getReader(), clazz);
//    }
//
//    public static String toJson(Object obj) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(obj);
//    }
//}