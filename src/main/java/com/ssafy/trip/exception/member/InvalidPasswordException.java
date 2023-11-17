package com.ssafy.trip.exception.member;

public class InvalidPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super("정확하지 않은 비밀번호입니다.");
	}

}

