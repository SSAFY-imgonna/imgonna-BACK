package com.ssafy.imgonna.plan.service;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.exception.plan.PlanRegistException;
import com.ssafy.imgonna.plan.model.dto.CourseRegistRequestDto;
import com.ssafy.imgonna.plan.model.dto.PlanRegistRequestDto;
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
    public int createPlan(PlanRegistRequestDto planRequestDto) {
        int planNo = 0;
        try {
           planMapper.createPlan(planRequestDto);
            List<AttractionInfo> attractions = planRequestDto.getAttractions();
            List<String> detailMemoList = planRequestDto.getDetailMemoList();
            CourseRegistRequestDto requestDto = new CourseRegistRequestDto();
            requestDto.setPlanNo(planRequestDto.getPlanNo());
            for (int idx = 0; idx < attractions.size(); idx++) {
                createCourse(requestDto, attractions, idx, detailMemoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PlanRegistException();
        }
        return planNo;
    }

    @Transactional
    private void createCourse(CourseRegistRequestDto requestDto, List<AttractionInfo> attractions, int idx, List<String> detailMemoList) {
        requestDto.setContentId(attractions.get(idx).getContentId());
        if (detailMemoList != null && detailMemoList.get(idx) != null) {
            requestDto.setMemo(detailMemoList.get(idx));
        }
        requestDto.setOrder(idx + 1);
        requestDto.setMemo(requestDto.getMemo());
        planMapper.createCourse(requestDto);
    }

}
