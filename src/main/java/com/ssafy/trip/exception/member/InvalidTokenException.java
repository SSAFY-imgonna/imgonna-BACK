package com.ssafy.trip.exception.member;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super("유효하지 않은 토큰입니다.");
	}

}

