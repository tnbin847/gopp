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
    YES ("Y", 1, true),
    NO ("N", 0, false);

    private final String symbol;
    private final int number;
    private final boolean bool;
}