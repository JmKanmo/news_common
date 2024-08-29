package com.service.news_common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class NewsReq {
    private String searchType; // ex) top / all
    private String fromDate; // 조회 시작 일자
    private String toDate; // 조회 종료 일자
    private String language; // 조회 언어
    private String country; // 조회 국가
    private String sortBy; // 정렬

    // TODO EX) q, qInTitle ...
}
