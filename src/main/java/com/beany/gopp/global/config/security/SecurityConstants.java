package com.beany.gopp.global.config.security;

/**
 * 스프링 시큐리티 관련 상수를 정의한 클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

public final class SecurityConstants {

    /**
     * 인증을 요구하지 않고 액세스를 허용할 요청 URL들을 정의한 배열 상수
     */
    public static final String[] PUBLICY_URL_MATCHERS = {
            "/",
            "/api/v1/login",
            "/api/v1/logout",
            "/sign-up",
            "/api/v1/user",
            "/api/v1/user/exists/**"
    };

    /**
     * 일반 로그인 방식에 사용될 아이디 파라미터 명
     */
    public static final String PRINCIPAL_KEY = "id";

    /**
     * 일반 로그인 방식에 사용될 비밀번호 파라미터 명
     */
    public static final String CREDENTIAL_KEY = "password";
}