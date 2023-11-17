package com.ssafy.imgonna.exception.member;

public class MemberInfoDuplicateException extends RuntimeException {


    public MemberInfoDuplicateException(String property) {
        super(String.format("이미 존재하는 %s입니다.", property));
    }

}
