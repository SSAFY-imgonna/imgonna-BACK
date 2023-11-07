package com.ssafy.trip.member.model.roleenum;

import com.ssafy.trip.enumhandlers.CodeEnum;

/**
 * 회원 유형에 대한 Enum 클래스
 * - ADMIN (관리자) : 9
 * - GENERAL (일반회원) : 1
 * - DORMANT (휴면회원) : 2
 * - DELETE (탈퇴회원) : 3
 *
 * @author yihoney
 */
public enum MemberRoleEnum implements CodeEnum {
    ADMIN(9),
    GENERAL(1),
    DORMANT(2),
    DELETE(3);

    private final int roleNum;

    MemberRoleEnum(int roleNum) {
        this.roleNum = roleNum;
    }

    @Override
    public int getCodeNum() {
        return roleNum;
    }

}
