package com.ssafy.imgonna.plan.model.mapper;

import com.ssafy.imgonna.plan.model.dto.CourseRegistRequestDto;
import com.ssafy.imgonna.plan.model.dto.PlanRegistRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {

	int createPlan(PlanRegistRequestDto planRegistRequestDto);
	void createCourse(CourseRegistRequestDto courseRegistRequestDto);

}
