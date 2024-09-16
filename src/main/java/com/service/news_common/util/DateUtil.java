package com.service.news_common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {
    private static final DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter yyyyMMddHHmmFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final DateTimeFormatter yyyyMMddHHmmssFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String yyyyMMdd(LocalDateTime localDateTime) {
        try {
            return localDateTime == null ? "" : localDateTime.format(yyyyMMddFormatter);
        } catch (Exception e) {
            log.error("[DateUtil-yyyyMMdd] ERROR:", e);
            return String.format("[DateUtil-yyyyMMdd] ERROR: %s", e);
        }
    }

    public static String yyyyMMdd(LocalDate localDate) {
        try {
            return localDate == null ? "" : localDate.format(yyyyMMddFormatter);
        } catch (Exception e) {
            log.error("[DateUtil-yyyyMMdd] ERROR:", e);
            return String.format("[DateUtil-yyyyMMdd] ERROR: %s", e);
        }
    }

    public static String yyyyMMddHHmm(LocalDateTime localDateTime) {
        try {
            return localDateTime == null ? "" : localDateTime.format(yyyyMMddHHmmFormatter);
        } catch (Exception e) {
            log.error("[DateUtil-yyyyMMddHHmm] ERROR:", e);
            return String.format("[DateUtil-yyyyMMddHHmm] ERROR: %s", e);
        }
    }

    public static String yyyyMMddHHmmss(LocalDateTime localDateTime) {
        try {
            return localDateTime == null ? "" : localDateTime.format(yyyyMMddHHmmssFormatter);
        } catch (Exception e) {
            log.error("[DateUtil-yyyyMMddHHmmss] ERROR:", e);
            return String.format("[DateUtil-yyyyMMddHHmmss] ERROR: %s", e);
        }
    }

    public static String customLocalDateTimeFormat(LocalDateTime localDateTime, String format) {
        try {
            DateTimeFormatter customDateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDateTime == null ? "" : localDateTime.format(customDateTimeFormatter);
        } catch (Exception e) {
            log.error("[DateUtil-customDateTimeFormat] ERROR:", e);
            return String.format("[DateUtil-customDateTimeFormat] ERROR: %s", e);
        }
    }

    public static String customLocalDateFormat(LocalDate localDate, String format) {
        try {
            DateTimeFormatter customDateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDate == null ? "" : localDate.format(customDateTimeFormatter);
        } catch (Exception e) {
            log.error("[DateUtil-customLocalDateFormat] ERROR: ", e);
            return String.format("[DateUtil-customLocalDateFormat] ERROR: %s", e);
        }
    }

    public static LocalDate formatStrToLocalDate(String str, String format) {
        try {
            // DateTimeFormatter를 이용하여 "YYYY-MM" 형식의 문자열을 LocalDate로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            // '-01'을 붙여서 LocalDate로 변환 가능한 "YYYY-MM-DD" 형식으로 만들어 줌
            return LocalDate.parse(str + "-01", formatter);
        } catch (Exception e) {
            // 형식이 맞지 않을 경우 예외 처리 (오류 발생할 경우, 현재 시간 기준으로 반환)
            log.error("[DateUtil-formatStrToLocalDate] Invalid date format for noticeMonth. ERROR:", e);
            return LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1);
        }
    }

    public static LocalDateTime formatStrToLocalDateTime(String str, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(str, formatter);
        } catch (Exception e) {
            // 형식이 맞지 않을 경우 예외 처리 (오류 발생할 경우, 현재 시간 기준으로 반환)
            log.error("[DateUtil-formatStrToLocalDate] Invalid date format for noticeMonth. ERROR:", e);
            return LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfYear(), 1, 1);
        }
    }

    public static LocalDateTime formatStrToZonedLocalDateTime(String str, String format) {
        try {
            // 패턴을 사용하여 DateTimeFormatter 생성
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            // 지정된 패턴을 사용하여 ZonedDateTime 파싱
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(str, formatter);
            // 브라우저의 로컬 시간대에 맞춰 LocalDateTime으로 변환
            return zonedDateTime.toLocalDateTime();
        } catch (Exception e) {
            // 형식이 맞지 않을 경우 예외 처리 (오류 발생할 경우, 현재 시간 기준으로 반환)
            log.error("[DateUtil-formatStrToZonedLocalDateTime] Invalid date format for noticeMonth. ERROR:", e);
            return LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfYear(), 1, 1);
        }
    }

    public static String formatLocalDateToStr(LocalDate localDate, String format) {
        try {
            // DateTimeFormatter를 이용하여 "yyyy-MM" 형식의 문자열로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            String formattedDate = localDate.format(formatter);
            return formattedDate;
        } catch (Exception e) {
            log.error("[DateUtil-formatLocalDateToStr] Invalid date format for noticeMonth. ERROR:", e);
            return "ERROR";
        }
    }
}