package com.ssafy.imgonna.plan.service;

import com.ssafy.imgonna.plan.model.dto.PlanRegistRequestDto;

public interface PlanService {

	int createPlan(PlanRegistRequestDto diaryRequestDto);
	
}
