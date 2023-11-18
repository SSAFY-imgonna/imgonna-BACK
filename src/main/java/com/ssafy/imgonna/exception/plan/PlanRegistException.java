package com.ssafy.imgonna.exception.plan;

public class PlanRegistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanRegistException() {
		super("여행 계획 등록에 실패하였습니다.");
	}

}

