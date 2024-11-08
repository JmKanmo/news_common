package com.service.news_common.util;

import org.apache.commons.lang3.StringUtils;

public class NewsUtil {
    /**
     * API 요청 암호화 해쉬코드 복호화 함수
     * <p>
     * 특수문자 OR 숫자 /한자, 한국말 등의 단어가 혼합 된 문자열 내에서
     * 랜덤한 위치/순서로 'N', 'E', 'B', 'I', 'R', 'O', 'S' 문자가 포함되어 있는지 체크
     *
     * @param hashcode
     * @return
     */
    public static boolean checkNewsReqHashCode(String hashcode) {
        if (StringUtils.isNotEmpty(hashcode)) {
            StringBuilder checkStr = new StringBuilder();

            if (hashcode.contains("N")) {
                checkStr.append("N");
            }

            if (hashcode.contains("E")) {
                checkStr.append("E");
            }

            if (hashcode.contains("B")) {
                checkStr.append("B");
            }

            if (hashcode.contains("I")) {
                checkStr.append("I");
            }

            if (hashcode.contains("R")) {
                checkStr.append("R");
            }

            if (hashcode.contains("O")) {
                checkStr.append("O");
            }

            if (hashcode.contains("S")) {
                checkStr.append("S");
            }
            return checkStr.toString().equals("NEBIROS");
        } else {
            return false;
        }
    }
}
