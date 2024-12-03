package com.beany.gopp.global.common.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * {@link CodeEnum} 인터페이스를 구현한 {@link Enum} 클래스의 코드값을 저장하거나<br>
 * 데이터베이스로부터 가져온 코드값을 알맞은 {@link Enum}으로 반환하는 역할을 수행하는 타입 핸들러 클래스
 *
 * @author 박 수 빈
 * @version 1.0.0
 */

@MappedTypes(CodeEnum.class)
public class CodeEnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {
    /**
     * 타입 핸들러에서 통해 처리하고자 하는 {@link Enum} 클래스
     */
    private final Class<E> type;
    /**
     * {@link Enum} 클래스 내에 정의된 상수들로 이루어진 배열
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
     * {@link PreparedStatement}에 {@link Enum}값을 세팅할 때 호출되며, 파라미터로 전달된 {@link Enum}의 코드값을 파라미터 바인딩 처리한다.
     *
     * @param ps            SQL 쿼리를 사전에 컴파일하여 데이터베이스에 반복해서 실행할 때 사용되는 인터페이스
     * @param i             바인딩 위치값
     * @param parameter     바인딩 처리 대상인 파라미터
     * @param jdbcType      자바 타입 또는 JDBC 타입
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    /**
     * 데이터베이스로부터 컬럼 명을 기준으로 한 코드 값을 가져와 {@link Enum}으로 반환한다.
     *
     * @param rs            데이터베이스에서 처리된 결과를 저장하는 객체로 주로 SELECT문의 결과를 저장한다.
     * @param columnName    컬럼 명
     * @return              {@code null} 또는 {@link Enum}
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.wasNull() ? null : convertToCodeEnum(rs.getString(columnName));
    }

    /**
     * 데이터베이스로부터 인자로 전달된 컬럼 인덱스를 기준으로 한 코드 값을 가져와 {@link Enum}으로 반환한다.
     *
     * @param rs            데이터베이스에서 처리된 결과를 저장하는 객체로 주로 SELECT문의 결과를 저장한다.
     * @param columnIndex   컬럼 인덱스
     * @return              {@code null} 또는 {@link Enum}
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.wasNull() ? null : convertToCodeEnum(rs.getString(columnIndex));
    }

    /**
     * SQL 저장 프로시저를 통해 인자로 전달된 컬럼 인덱스를 기준으로 한 코드 값을 가져와 {@link Enum}으로 반환한다.
     *
     * @param cs            SQL 저장 프로시저를 실행하는 데 사용되는 인터페이스
     * @param columnIndex   컬럼 인덱스
     * @return              {@code null} 또는 {@link Enum}
     * @throws SQLException
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.wasNull() ? null : convertToCodeEnum(cs.getString(columnIndex));
    }

    /**
     * {@link Enum} 클래스 내의 상수값들 중 데이터베이스로부터 가져온 코드값과 일치하는 상수값이 존재할 경우, 해당 {@link Enum}을 반환한다.
     *
     * @param code  데이터베이스로부터 가져온 코드
     * @return      Enum
     */
    private E convertToCodeEnum(String code) {
        return Arrays.stream(constants)
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert '" + code + "' to '" + type.getSimpleName() + "."));
    }
}