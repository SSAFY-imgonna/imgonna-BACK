package com.ssafy.trip.member.model.roleenum;

/**
 * 회원 유형에 대한 Enum 클래스
 * - ADMIN (관리자) : 9
 * - GENERAL (일반회원) : 1
 * - DORMANT (휴면회원) : 2
 * - DELETE (탈퇴회원) : 3
 *
 * @author yihoney
 */
public enum MemberRoleEnum {
    ADMIN(9),
    GENERAL(1),
    DORMANT(2),
    DELETE(3);

    private final int roleNum;

    MemberRoleEnum(int roleNum) {
        this.roleNum = roleNum;
    }

    public int getRoleNum() {
        return roleNum;
    }

    /**
     * int 타입의 roleNum에 해당하는 MemberRoleEnum을 반환하는 메서드
     * @param roleNum 회원 유형 번호
     * @return 회원 유형 번호에 매핑되는 Enum
     */
    public static MemberRoleEnum getRoleEnum(int roleNum) {
        MemberRoleEnum roleEnum = null;
        switch (roleNum) {
            case 9:
                roleEnum = MemberRoleEnum.ADMIN; // 관리자
                break;

            case 2:
                roleEnum = MemberRoleEnum.DORMANT; // 휴면회원
                break;

            case 3:
                roleEnum=MemberRoleEnum.DELETE; // 탈퇴회원
                break;

            default:
                roleEnum = MemberRoleEnum.GENERAL; // 일반회원
                break;
        }
        return roleEnum;
    }
}
