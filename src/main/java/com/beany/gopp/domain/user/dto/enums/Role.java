package com.beany.gopp.domain.user.dto.enums;

import com.beany.gopp.global.common.mybatis.CodeEnum;
import lombok.AllArgsConstructor;

/**
 * 사용자에게 부여될 권한에 대한 코드를 정의한 {@link Enum}클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@AllArgsConstructor
public enum Role implements CodeEnum {
    USER ("ROLE_USER", "일반회원"),
    ADMIN ("ROLE_ADMIN", "관리자");

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