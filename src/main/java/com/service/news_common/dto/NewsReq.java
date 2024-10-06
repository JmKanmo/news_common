package com.service.news_common.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsReq {
    private String searchType; // ex) top / all
    private String keyword; // q (키워드)
    private String title; // qInTitle (기사 제목)
    private String category; // category (카테고리)
    private LocalDateTime fromDate; // 조회 시작 일자 ex) ISO 8601 format (e.g. 2021-04-11 or 2021-04-11T19:40:08
    private LocalDateTime toDate; // 조회 종료 일자 ex) ISO 8601 format (e.g. 2021-04-11 or 2021-04-11T19:40:08
    private String source;
    private String domain;
    private String excludeDomain;
    private String language; // 조회 언어
    private String country; // 조회 국가
    private String sortBy; // 정렬
    private String pageSize; // 페이지 당 반환되는 결과 수 (크기)
    private String page; // 검색 결과 페이지 (오프셋)
    // TODO 필요 시에 추가

    /**
     * ex)
     * all?q=bitcoin&qInTitle=news&sources=aaa&domains=fdsds&excludeDomains=fsd&from=2021-04-11&to=2099-12-31&language=kr&country=pk&category=business&sortBy=relevancy&pageSize=10&page=30
     *
     * @return
     */
    public String queryString() {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StringUtils.isEmpty(searchType) ? "all" : searchType);
            stringBuilder.append("?");

            if (StringUtils.isNotEmpty(keyword)) {
                stringBuilder.append(String.format("q=%s", keyword));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(title)) {
                stringBuilder.append(String.format("qInTitle=%s", title));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(source)) {
                stringBuilder.append(String.format("sources=%s", source));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(domain)) {
                stringBuilder.append(String.format("domains=%s", domain));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(excludeDomain)) {
                stringBuilder.append(String.format("excludeDomains=%s", excludeDomain));
                stringBuilder.append("&");
            }

            String fromDateStr = (fromDate == null) ? "" : fromDate.toString();

            if (StringUtils.isNotEmpty(fromDateStr)) {
                stringBuilder.append(String.format("from=%s", fromDateStr));
                stringBuilder.append("&");
            }

            String toDateStr = (toDate == null) ? "" : toDate.toString();

            if (StringUtils.isNotEmpty(toDateStr)) {
                stringBuilder.append(String.format("to=%s", toDateStr));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(language)) {
                stringBuilder.append(String.format("language=%s", language));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(country)) {
                stringBuilder.append(String.format("country=%s", country));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(category)) {
                stringBuilder.append(String.format("category=%s", category));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(sortBy)) {
                stringBuilder.append(String.format("sortBy=%s", sortBy));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(pageSize)) {
                stringBuilder.append(String.format("pageSize=%s", pageSize));
                stringBuilder.append("&");
            }

            if (StringUtils.isNotEmpty(page)) {
                stringBuilder.append(String.format("page=%s", page));
                // stringBuilder.append("&");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            log.error("[NewsReq:queryString] error", e);
            return "ERROR";
        }
    }
}
