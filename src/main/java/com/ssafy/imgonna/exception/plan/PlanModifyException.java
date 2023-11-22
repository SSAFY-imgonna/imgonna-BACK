package com.ssafy.imgonna.exception.plan;

public class PlanModifyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanModifyException() {
		super("여행 계획 수정에 실패하였습니다.");
	}

}

