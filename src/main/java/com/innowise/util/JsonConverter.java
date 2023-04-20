package com.innowise.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**

 Utility class for converting JSON to Java objects and vice versa using the Jackson library.
 */
public class JsonConverter {

    /**

     ObjectMapper instance used for JSON serialization and deserialization.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**

     Converts a JSON string received as a request parameter into an instance of the specified class.
     @param request The HttpServletRequest containing the JSON data
     @param clazz The target class of the deserialization
     @return The deserialized object of the specified class
     @throws IOException if there is an error reading the input stream or parsing the JSON string
     */
    public static <T> T toObject(HttpServletRequest request, Class<T> clazz) throws IOException {
        return objectMapper.readValue(request.getReader(), clazz);
    }
    /**

     Converts a Java object to its JSON representation as a string.
     @param obj The object to serialize to JSON
     @return A JSON string representing the serialized object
     @throws JsonProcessingException if there is an error during the serialization process
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}