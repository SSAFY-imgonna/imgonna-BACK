package com.ssafy.imgonna.plan.service;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.exception.plan.PlanModifyException;
import com.ssafy.imgonna.exception.plan.PlanRegistException;
import com.ssafy.imgonna.plan.model.dto.CourseRequestDto;
import com.ssafy.imgonna.plan.model.dto.PlanResponseDto;
import com.ssafy.imgonna.plan.model.dto.PlanRequestDto;
import com.ssafy.imgonna.plan.model.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private PlanMapper planMapper;

    @Autowired
    public PlanServiceImpl(PlanMapper planMapper) {
        super();
        this.planMapper = planMapper;
    }

    /**
     * 여행 계획을 등록하는 메서드
     * <p>
     * 여행 계획 테이블 데이터 삽입 후 받은 PK 값을 이용해 참조 테이블 여행 코스 테이블까지 데이터 삽입
     *
     * @param planRequestDto
     * @return
     * @author yihoney
     */
    @Override
    @Transactional
    public int createPlan(PlanRequestDto planRequestDto) {
        try {
            planMapper.createPlan(planRequestDto);
            List<AttractionInfo> attractions = planRequestDto.getAttractions();
            List<String> detailMemoList = planRequestDto.getDetailMemoList();

            for (int idx = 0; idx < attractions.size(); idx++) {
                CourseRequestDto requestDto = getCourseRequestDto(planRequestDto.getPlanNo(), attractions, idx, detailMemoList);
                planMapper.createCourse(requestDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PlanRegistException();
        }
        return planRequestDto.getPlanNo();
    }

    @Override
    @Transactional
    public int modifyPlan(PlanRequestDto planRequestDto) {
        try {
            planMapper.modifyPlan(planRequestDto);
            List<AttractionInfo> attractions = planRequestDto.getAttractions();
            List<String> detailMemoList = planRequestDto.getDetailMemoList();
            int planNo = planRequestDto.getPlanNo();

            // 기존 코스 삭제
            planMapper.deleteCourseByPlanNo(planNo);

            for (int idx = 0; idx < attractions.size(); idx++) {
                CourseRequestDto requestDto = getCourseRequestDto(planNo, attractions, idx, detailMemoList);
                planMapper.createCourse(requestDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PlanModifyException();
        }
        return planRequestDto.getPlanNo();
    }

    @Override
    public void deletePlan(int planNo) {
        try {
            planMapper.deleteCourseByPlanNo(planNo);
            planMapper.deletePlanByPlanNo(planNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PlanModifyException();
        }
    }

    @Override
    public PlanResponseDto getPlanByPlanNo(int planNo) {
        PlanResponseDto plan = planMapper.getPlanByPlanNo(planNo);
        plan.setCourses(planMapper.getCourseListByPlanNo(planNo));
        return plan;
    }

    @Override
    public List<PlanResponseDto> getPlanList() {
        List<PlanResponseDto> planList = planMapper.getPlanList();
        for (int i = 0; i < planList.size(); i++) {
            PlanResponseDto planResponseDto = planList.get(i);
            planResponseDto.setCourses(planMapper.getCourseListByPlanNo(planResponseDto.getPlanNo()));
        }
        return planList;
    }

    @Override
    public List<PlanResponseDto> getPlanListById(String id) {
        List<PlanResponseDto> planList = planMapper.getPlanListById(id);
        for (int i = 0; i < planList.size(); i++) {
            PlanResponseDto planResponseDto = planList.get(i);
            planResponseDto.setCourses(planMapper.getCourseListByPlanNo(planResponseDto.getPlanNo()));
        }
        return planList;
    }

    private CourseRequestDto getCourseRequestDto(int planNo, List<AttractionInfo> attractions, int idx, List<String> detailMemoList) {
        CourseRequestDto requestDto = new CourseRequestDto();
        requestDto.setPlanNo(planNo);
        requestDto.setContentId(attractions.get(idx).getContentId());
        if (detailMemoList != null && detailMemoList.get(idx) != null) {
            requestDto.setMemo(detailMemoList.get(idx));
        }
        requestDto.setOrder(idx + 1);
        requestDto.setMemo(requestDto.getMemo());
        return requestDto;
    }

}
