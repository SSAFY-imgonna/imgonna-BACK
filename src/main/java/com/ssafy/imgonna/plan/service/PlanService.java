package com.ssafy.imgonna.plan.service;

import com.ssafy.imgonna.plan.model.dto.PlanResponseDto;
import com.ssafy.imgonna.plan.model.dto.PlanRequestDto;

import java.util.List;

public interface PlanService {

	int createPlan(PlanRequestDto diaryRequestDto);

	int modifyPlan(PlanRequestDto requestDto);

	void deletePlan(int planNo);

	PlanResponseDto getPlanByPlanNo(int planNo);

	List<PlanResponseDto> getPlanList();

	List<PlanResponseDto> getPlanListById(String id);
}
