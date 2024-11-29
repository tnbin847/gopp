package com.beany.gopp.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 공통적으로 사용될 응답 및 에러 코드와 메시지, HTTP 상태코드 등을 정의한 {@link Enum}클래스
 * 
 * @author 박 수 빈
 * @version 1.0.0
 */

@RequiredArgsConstructor
@Getter
public enum ApiEnum {
    /**
     * 성공 응답 코드
     */
    OK (100000, HttpStatus.OK, "요청이 정상적으로 처리되었습니다."),

    /**
     * [공통] 에러 응답 코드
     */
    INVALID_PARAM_VALUE (-800010, HttpStatus.BAD_REQUEST, "요청 파라미터의 값이 유효하지 않습니다."),
    INVALID_PARAM_TYPE (-800011, HttpStatus.BAD_REQUEST, "유효하지 않은 타입의 요청 파라미터입니다."),
    NOT_FOUND_RESOURCE (-804020, HttpStatus.NOT_FOUND, "요청하신 자원을 찾을 수 없습니다."),
    NOT_SUPPORTED_HTTP_METHOD (-805020, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드입니다."),
    INTERNAL_SERVER_ERROR (-900000, HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버에 오류가 발생했습니다.")
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}