package com.vonage.quickstart.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonPrinter {
    public static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void print(Object obj) {
        try {
            String json = mapper.writeValueAsString(obj);
            System.out.println(json);
        } catch (Exception e) {
            System.out.println("Failed to convert object to JSON: ");
            e.printStackTrace();
        }
    }
}
