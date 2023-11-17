package com.ssafy.imgonna.exception.member;

public class MemberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MemberNotFoundException() {
		super("일치하는 회원의 정보가 없습니다.");
	}

}

