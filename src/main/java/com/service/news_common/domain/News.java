package com.service.news_common.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class News {
    /**
     * news id
     */
    private Long newsId;

    /**
     * 국가 코드 (2자리)
     */
    private String countryCode;

    /**
     * 뉴스 카테고리
     */
    private String category;

    /**
     * 출판 시간(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime publishTime;

    /**
     * 뉴스 제목
     */
    private String title;

    /**
     * 뉴스 본문
     */
    private String description;

    /**
     * 뉴스 링크
     */
    private String link;

    /**
     * 저자
     */
    private String author;

    /**
     * 썸네일 이미지 링크
     */
    private String thumbnailUrl;

    /**
     * 제공처
     */
    private String source;
}
