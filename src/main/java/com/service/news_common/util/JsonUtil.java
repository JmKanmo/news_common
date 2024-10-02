package com.service.news_common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper objectMapper;

    static {
        ObjectMapper _objectMapper = null;
        try {
            _objectMapper = new ObjectMapper();
            _objectMapper.registerModule(new JavaTimeModule()); // LocalDateTime 등 설정
        } catch (Exception e) {
            log.error("[JsonUtil] objectMapper registerModule init failed", e);
            _objectMapper = new ObjectMapper();
        }
        objectMapper = _objectMapper;
    }

    private JsonUtil() {
    }

    public static String writeValueAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("[JsonUtil:writeValueAsString] error:{}", e);
            return null;
        }
    }

    public static byte[] writeValueAsBytes(byte[] obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            log.error("[JsonUtil:writeValueAsBytes] error:{}", e);
            return null;
        }
    }

    public static <T> T readClzValue(String str, Class<T> clz) {
        try {
            return objectMapper.readValue(str, clz);
        } catch (Exception e) {
            log.error("[JsonUtil:readClzValue] error:{}", e);
            return null;
        }
    }

    public static <T> List<T> readListValue(String str, Class<T> clz) {
        try {
            return objectMapper.readValue(str, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clz));
        } catch (Exception e) {
            log.error("[JsonUtil:readListValue] error:{}", e);
            return null;
        }
    }

    public static <K, V> Map<K, V> readMapValue(String str, Class<K> clz_key, Class<V> clz_value) {
        try {
            return objectMapper.readValue(str, objectMapper.getTypeFactory().constructMapType(Map.class, clz_key, clz_value));
        } catch (Exception e) {
            log.error("[JsonUtil:readMapValue] error:{}", e);
            return null;
        }
    }
}
