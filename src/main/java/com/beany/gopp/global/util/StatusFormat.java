package com.beany.gopp.global.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 상태 여부에 대한 값을 처리하기 위해 서로 다른 타입의 데이터를 상응되는 의미별로 정의한 {@link Enum}클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@RequiredArgsConstructor
@Getter
public enum StatusFormat {
    YES (1, "Y", true),
    NO (0, "N", false);

    /**
     * 정수형의 상태여부 값
     */
    private final int number;
    /**
     * 문자열 타입의 상태여부 값
     */
    private final String symbol;
    /**
     * 논리형의 상태여부 값
     */
    private final boolean bool;
}