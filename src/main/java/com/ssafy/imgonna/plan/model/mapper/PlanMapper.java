package com.ssafy.imgonna.plan.model.mapper;

import com.ssafy.imgonna.plan.model.dto.CourseResponseDto;
import com.ssafy.imgonna.plan.model.dto.CourseRequestDto;
import com.ssafy.imgonna.plan.model.dto.PlanResponseDto;
import com.ssafy.imgonna.plan.model.dto.PlanRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {

	int createPlan(PlanRequestDto planRequestDto);
	void createCourse(CourseRequestDto courseRequestDto);

	void modifyPlan(PlanRequestDto planRequestDto);

	void modifyCourse(CourseRequestDto requestDto);

	void deleteCourseByPlanNo(int planNo);

	void deletePlanByPlanNo(int planNo);

	PlanResponseDto getPlanByPlanNo(int planNo);

	List<CourseResponseDto> getCourseListByPlanNo(int planNo);

	List<PlanResponseDto> getPlanList(Map<String, Object> param);

	List<PlanResponseDto> getPlanListById(String id);

	int getTotalPlanCount();
}
