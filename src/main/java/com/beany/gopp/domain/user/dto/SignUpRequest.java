package com.beany.gopp.domain.user.dto;

import com.beany.gopp.domain.user.dto.enums.LoginType;
import com.beany.gopp.global.util.StatusFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 박 수 빈
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignUpRequest {
    private Long userId;

    private String name;

    private String id;

    private String email;

    @Setter
    private String password;

    private String passwordConfirm;

    @Setter
    private LoginType loginType;

    private final int enabled = StatusFormat.YES.getNumber();

    private final String useYn = StatusFormat.YES.getSymbol();

    private final String deleteYn = StatusFormat.NO.getSymbol();
}