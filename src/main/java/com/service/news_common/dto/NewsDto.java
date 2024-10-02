package com.service.news_common.dto;

import com.service.news_common.util.DateUtil;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsDto {
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

    // YYYY-MM-DD HH:MM 형태 반환 (초단위 까지는 화면에서 안보여줘도 될듯)
    public String getPublishTime() {
        return DateUtil.yyyyMMddHHmm(publishTime);
    }

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
