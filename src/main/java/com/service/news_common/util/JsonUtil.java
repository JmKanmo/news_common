package com.service.news_common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsonUtil {
    private final ObjectMapper objectMapper = getObjectMapper();

    public String writeValueAsString(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public byte[] writeValueAsBytes(byte[] obj) throws Exception {
        return objectMapper.writeValueAsBytes(obj);
    }

    public <T> T readClzValue(String str, Class<T> clz) throws Exception {
        return objectMapper.readValue(str, clz);
    }

    public <T> List<T> readListValue(String str, Class<T> clz) throws Exception {
        return objectMapper.readValue(str, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clz));
    }

    public <K, V> Map<K, V> readMapValue(String str, Class<K> clz_key, Class<V> clz_value) throws Exception {
        return objectMapper.readValue(str, objectMapper.getTypeFactory().constructMapType(Map.class, clz_key, clz_value));
    }

    public ObjectMapper getObjectMapper() {
        ObjectMapper _objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // LocalDateTime 등 설정
        return this.objectMapper;
    }
}
