package com.ssafy.imgonna.exception.member;

public class UnauthorizedMemberException extends RuntimeException {


    public UnauthorizedMemberException() {
        super("로그인이 필요한 서비스입니다. 로그인해주세요!");
    }

}
