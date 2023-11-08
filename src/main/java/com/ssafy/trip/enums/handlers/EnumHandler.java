package com.ssafy.trip.enums.handlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

/**
 * Enum을 DB에서 가져오거나 저장할 때 핸들링 하기 위한 핸들러
 */

public abstract class EnumHandler<E extends Enum<E> & CodeEnum> implements TypeHandler<CodeEnum> {

    private Class<E> type;

    public EnumHandler(Class<E> type) {
        this.type = type;
    }

    /**
     * db에 저장할 값을 설정하는 메서드
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, CodeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCodeNum());
    }

    /**
     * 컬럼 이름을 기준으로 조회된 값을 Enum으로 변환하는 메서드
     */
    @Override
    public CodeEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int codeNum = rs.getInt(columnName);
        return getCodeEnum(codeNum);
    }

    /**
     * 컬럼 인덱스를 기준으로 조회된 값을 Enum으로 변환하는 메서드
     */
    @Override
    public CodeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int codeNum = rs.getInt(columnIndex);
        return getCodeEnum(codeNum);
    }

    /**
     * Callablestatement에서 컬럼 인덱스를 기준으로 조회된 값을 Enum으로 변환하는 메서드
     */
    @Override
    public CodeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int codeNum = cs.getInt(columnIndex);
        return getCodeEnum(codeNum);
    }

    /**
     * int 타입의 code에 해당하는 Enum을 반환하는 메서드
     * @param code 코드 번호
     * @return 코드 번호에 매핑되는 Enum
     */
    private CodeEnum getCodeEnum(int code) {
        return EnumSet.allOf(type)
                .stream()
                .filter(value -> value.getCodeNum()==code)
                .findFirst()
                .orElseGet(null);
    }

}