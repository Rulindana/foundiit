package com.student.foundiit.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public final class JacksonMapper {
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .findAndAddModules()
            .build();

    private JacksonMapper() {
    }

    public static ObjectMapper mapper() {
        return MAPPER;
    }
}

