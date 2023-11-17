package com.ssafy.imgonna.exception.accompany;

public class InvalidAccompanyDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAccompanyDataException() {
		super("유효하지 않은 입력데이터입니다.");
	}

}

