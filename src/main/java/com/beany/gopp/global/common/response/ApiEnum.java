package com.beany.gopp.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * API에서 일관되게 응답을 처리할 수 있도록 API 응답 코드 관리 및 응답 메시지 등의 표준화를 목적으로 정의한 {@link Enum} 클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@RequiredArgsConstructor
@Getter
public enum ApiEnum {
    /**
     * [공통] 성공 응답
     */
    OK (100000, HttpStatus.OK, "요청이 정상적으로 처리되었습니다."),
    CREATED (101010, HttpStatus.CREATED, "리소스가 생성되었습니다."),
    UPDATED (104020, HttpStatus.NO_CONTENT, "리소스가 변경되었습니다."),
    DELETED (104030, HttpStatus.NO_CONTENT, "리소스가 삭제되었습니다."),

    /**
     * [공통] 에러 응답
     */
    INVALID_PARAM_VALUE (-800010, HttpStatus.BAD_REQUEST, "요청 파라미터의 값이 유효하지 않습니다."),
    INVALID_PARAM_TYPE (-800011, HttpStatus.BAD_REQUEST, "유효하지 못한 타입의 요청 파라미터입니다."),
    NOT_FOUND_RESOURCE (-804020, HttpStatus.NOT_FOUND, "요청하신 자원을 찾을 수 없습니다."),
    NOT_SUPPORTED_METHOD (-805020, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드 방식의 요청입니다."),
    INTERNAL_SERVER_ERROR (-900000, HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버에 오류가 발생했습니다.")
    ;

    /**
     * 응답 코드
     */
    private final int code;
    /**
     * HTTP 상태 코드
     */
    private final HttpStatus httpStatus;
    /**
     * 요청의 상태를 설명하는 응답 메시지
     */
    private final String message;
}