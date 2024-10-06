package com.service.news_common;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.service.news_common.dto.NewsReq;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * API 호출 주기
 * 국가별로 다르게 ... (데이터가 많은 순으로)
 * 호출 주기가 낮은 국가가 있는 만큼, 좀 더 다양한 국가별로 뉴스 데이터를 제공하도록 한다.
 * <p>
 * 각 카테고리 별로 호출하기 보다, 전체 카테고리로 pagination 순으로 호출을 하고
 * key 별로 DB에 카테고리 별로 저장 하도록 한다. (호출 수 감소)
 */
public class NewsomaticApiTest {
    @Test
    public void newsTestByGson() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://newsomaticapi.p.rapidapi.com/top?from=2024-08-07&to=2024-08-08&language=kr&country=kr&sortBy=relevancy")
                    .get()
                    .addHeader("x-rapidapi-key", "d9d8209b37msh58f5069b2a19b62p134a47jsn975b98d0c1cb")
                    .addHeader("x-rapidapi-host", "newsomaticapi.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            String bodyStr = response.body().string();
            Gson gson = makeGson();
            // TypeToken을 사용하여 Map<String, Object> 타입을 지정
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            // JSON 문자열을 Map<String, Object>로 변환
            Map<String, Object> map = gson.fromJson(bodyStr, type);
            // 변환된 Map 출력
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newsTestByJson() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://newsomaticapi.p.rapidapi.com/top?from=2024-08-15&to=2024-08-16&language=kr&country=kr&sortBy=relevancy")
                    .get()
                    .addHeader("x-rapidapi-key", "")
                    .addHeader("x-rapidapi-host", "")
                    .build();
            Response response = client.newCall(request).execute();
            String bodyStr = response.body().string();
            Map<String, Object> map = makeObjectMapper().readValue(bodyStr, new TypeReference<Map<String, Object>>() {
            });
            // 변환된 Map 출력
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jacksonTest() {
        ObjectMapper objectMapper = makeObjectMapper();
        // TODO
    }

    @Test
    public void makeNewsReq() {
        LocalDateTime from = LocalDateTime.of(2024, 4, 11, 19, 40, 8);
        String fromStr = from.toString();
        System.out.println(fromStr);

        LocalDateTime to = LocalDateTime.of(2024, 10, 6, 19, 22, 22);
        String toStr = from.toString();
        System.out.println(toStr);

        NewsReq newsReq = NewsReq.builder()
                .keyword("bitcoin")
                .title("hello world")
                .source("뉴스")
                .domain("msbsound")
                .excludeDomain("poclanos")
                .fromDate(from)
                .toDate(to)
                .language("kr")
                .country("pk")
                .category("business")
                .sortBy("relevancy")
                .pageSize(Integer.toString(20))
                .page(Integer.toString(30))
                .build();

        String newsQueryStr = newsReq.queryString();
        System.out.println(newsQueryStr);
    }

    private ObjectMapper makeObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Java 8 날짜 및 시간 타입 지원을 위한 모듈 등록
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    private Gson makeGson() {
        return new Gson();
    }
}
