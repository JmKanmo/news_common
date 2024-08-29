package com.service.news_common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class NewsDto {
    /**
     * news id
     */
    private final Long newsId;

    /**
     * 국가 코드 (2자리)
     */
    private final String countryCode;

    /**
     * 뉴스 카테고리
     */
    private final String category;

    /**
     * 출판 시간(YYYY-MM-DD HH:MM:SS)
     */
    private final LocalDateTime publishTime;

    /**
     * 뉴스 제목
     */
    private final String title;

    /**
     * 뉴스 본문
     */
    private final String description;

    /**
     * 뉴스 링크
     */
    private final String link;

    /**
     * 저자
     */
    private final String author;

    /**
     * 썸네일 이미지 링크
     */
    private final String thumbnailUrl;

    /**
     * 제공처
     */
    private final String source;
}
