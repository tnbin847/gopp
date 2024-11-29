package com.beany.gopp.global.common.mybatis;

/**
 * 자체 구현한 타입 핸들러를 적용해 데이터베이스 처리를 하고자 하는 {@link Enum}클래스를 일관적으로 구현하기 위해 정의한 인터페이스
 *
 * @author      박 수 빈
 * @version     1.0.0
 */

public interface CodeEnum {
    /**
     * 데이터베이스에 저장할 코드를 반환한다.
     */
    String getCode();

    /**
     * 뷰에 출력할 값을 반환한다.
     */
    String getLabel();
}