package com.service.news_common.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)  // 클래스 레벨에서 null 필드 무시
public class News {
    /**
     * news id
     */
    private Long newsId;

    /**
     * 언어 (정보성 데이터)
     */
    private String language = "kr";

    /**
     * 국가 코드 (2자리)
     */
    @JsonProperty("country")
    private String countryCode = "kr";

    /**
     * 뉴스 카테고리
     */
    private String category;

    /**
     * 출판 시간(YYYY-MM-DD HH:MM:SS)
     */
    @JsonProperty("publishedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime = LocalDateTime.now();

    /**
     * 뉴스 제목
     */
    private String title;

    /**
     * 컨텐츠 (요약)
     */
    private String content = "";

    /**
     * 뉴스 본문
     */
    private String description = "";

    /**
     * 뉴스 링크
     */
    @JsonProperty("url")
    private String link = "";

    /**
     * 저자
     */
    private String author = "";

    /**
     * 썸네일 이미지 링크
     */
    @JsonProperty("urlToImage")
    private String thumbnailUrl = "";

    /**
     * 제공처
     */
    @JsonProperty("source")
    private Source source;

    /**
     * Source 정보 Inner 클래스
     */
    @Data
    public static class Source {
        private String name;
    }
}
