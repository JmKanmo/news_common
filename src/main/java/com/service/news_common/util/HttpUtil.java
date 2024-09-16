package com.service.news_common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.ByteString;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {
    private static final OkHttpClient client;
    private static final ObjectMapper objectMapper;

    private static final long CONNECT_TIME_OUT = (60 * 5); // 5분
    private static final long READ_TIME_OUT = (60 * 5); // 5분
    private static final long WRITE_TIME_OUT = (60 * 5); // 5분

    static {
        // HTTP 로그를 출력하는 인터셉터 추가
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();

        // ObjectMapper 초기화 (JSON 응답을 클래스 객체로 변환)
        objectMapper = new ObjectMapper();
    }

    private HttpUtil() {
    }

    public static <T> T sendRequest(String url, String method, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, null)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, byte[] requestBody, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, String requestBody, MediaType contentType, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody, contentType))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, ByteString requestBody, MediaType contentType, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody, contentType))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, byte[] requestBody, MediaType contentType, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody, contentType))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, File requestBody, MediaType contentType, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody, contentType))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, byte[] requestBody, MediaType contentType, int offset, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody, contentType, offset))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    public static <T> T sendRequest(String url, String method, byte[] requestBody, MediaType contentType, int offset, int byteCount, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, RequestBody.create(requestBody, contentType, offset, byteCount))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return castResponseType(responseType, response.body());
        } catch (Throwable t) {
            log.error("[OkHttpUtil:sendRequest] error happened, ", t);
        }
        return null;
    }

    private static <T> T castResponseType(Class<T> responseType, ResponseBody responseBody) throws Exception {
        // 응답 타입에 따른 처리
        if (responseType == null || responseBody == null) {
            throw new NullPointerException("responseType, responseBody is null!");
        }

        if (responseType.equals(Void.class)) {
            return null;
        } else if (responseType.equals(String.class)) {
            return responseType.cast(responseBody.string());
        } else if (responseType.equals(byte[].class)) {
            return responseType.cast(responseBody.bytes());
        } else if (responseType.equals(Integer.class)) {
            return responseType.cast(Integer.parseInt(responseBody.string()));
        } else if (responseType.equals(Long.class)) {
            return responseType.cast(Long.parseLong(responseBody.string()));
        } else if (responseType.equals(Double.class)) {
            return responseType.cast(Double.parseDouble(responseBody.string()));
        } else if (responseType.equals(Boolean.class)) {
            return responseType.cast(Boolean.parseBoolean(responseBody.string()));
        } else {
            try {
                // 응답이 객체일 경우 (예: JSON -> POJO 변환)
                return objectMapper.readValue(responseBody.string(), responseType);
            } catch (Exception e) {
                throw new RuntimeException("Unsupported response type: " + responseType + ", error:" + e);
            }
        }
    }
}