package com.beany.gopp.global.common.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * {@link CodeEnum}인터페이스를 구현한 {@link Enum}클래스의 코드값을 저장하거나, 데이터베이스로부터 가져온 코드값을<br>
 * 알맞은 {@link Enum}으로 반환하는 역할을 수행하는 타입 핸들러
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@MappedTypes(CodeEnum.class)
public class CodeEnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {
    /**
     * 타입 핸들러를 통해 처리하고자 하는 {@link Enum}클래스
     */
    private final Class<E> type;
    /**
     * {@link Enum}클래스 내에 정의된 상수들로 이루어진 배열
     */
    private final E[] constants;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.type = type;
        this.constants = type.getEnumConstants();
        if (!type.isInterface() && this.constants == null) {
            throw new IllegalArgumentException("'" + type.getSimpleName() + "' does not represent an enum type.");
        }
    }

    /**
     * {@link PreparedStatement}에 {@link Enum}값을 세팅할 때 호출되며, 파라미터로 전달된 {@link CodeEnum}을 구현화한 {@link Enum}의
     * 코드값을 꺼내 파라미터 바인딩을 해준다.
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    /**
     * 데이터베이스로부터 코드 컬럼 값을 가져와 {@link Enum}으로 반환한다.
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return rs.wasNull() ? null : getCodeEnum(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return rs.wasNull() ? null : getCodeEnum(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return cs.wasNull() ? null : getCodeEnum(code);
    }

    /**
     * {@link Enum} 클래스 내의 상수값들 중 데이터베이스로부터 가져온 코드값과 일치하는 상수값이 존재할 경우, 해당 {@link Enum}을 반환한다.
     *
     * @param code  데이터베이스로부터 가져온 코드
     * @return      Enum
     */
    private E getCodeEnum(String code) {
        return Arrays.stream(constants)
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert '" + code + "' to '" + type.getSimpleName() + "'."));
    }
}