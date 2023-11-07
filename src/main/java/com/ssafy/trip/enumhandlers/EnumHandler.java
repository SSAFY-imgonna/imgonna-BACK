package com.ssafy.trip.enumhandlers;

import com.ssafy.trip.member.model.roleenum.MemberRoleEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Enum을 DB에서 가져오거나 저장할 때 핸들링 하기 위한 핸들러
 */

public class EnumHandler implements TypeHandler<MemberRoleEnum> {

    /**
     * db에 저장할 값을 설정하는 메서드
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, MemberRoleEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getRoleNum());
    }

    /**
     * 컬럼 이름을 기준으로 조회된 값을 Enum으로 변환하는 메서드
     * @param rs
     *          the rs
     * @param columnName
     *          Column name, when configuration <code>useColumnLabel</code> is <code>false</code>
     *
     * @return
     * @throws SQLException
     */
    @Override
    public MemberRoleEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int roleNum = rs.getInt(columnName);
        return MemberRoleEnum.getRoleEnum(roleNum);
    }

    /**
     * 컬럼 인덱스를 기준으로 조회된 값을 Enum으로 변환하는 메서드
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public MemberRoleEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int roleNum = rs.getInt(columnIndex);
        return MemberRoleEnum.getRoleEnum(roleNum);
    }

    /**
     * Callablestatement에서 컬럼 인덱스를 기준으로 조회된 값을 Enum으로 변환하는 메서드
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public MemberRoleEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int roleNum = cs.getInt(columnIndex);
        return MemberRoleEnum.getRoleEnum(roleNum);
    }


}