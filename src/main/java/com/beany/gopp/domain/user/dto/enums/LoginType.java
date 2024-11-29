package com.beany.gopp.domain.user.dto.enums;

import com.beany.gopp.global.common.mybatis.CodeEnum;
import lombok.AllArgsConstructor;

/**
 * 사용자의 가입 방식에 따른 로그인 유형에 대한 코드를 정의한 {@link Enum}클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@AllArgsConstructor
public enum LoginType implements CodeEnum {
    BASIC ("LOCAL", "로컬"),
    SOCIAL ("OAUTH", "소셜");

    private final String code;
    private final String label;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}