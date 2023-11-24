package com.ssafy.imgonna.plan.controller;

import com.ssafy.imgonna.common.annotation.CheckToken;
import com.ssafy.imgonna.plan.model.dto.PlanListResponseDto;
import com.ssafy.imgonna.plan.model.dto.PlanNoResponseDto;
import com.ssafy.imgonna.plan.model.dto.PlanRequestDto;
import com.ssafy.imgonna.plan.model.dto.PlanResponseDto;
import com.ssafy.imgonna.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/plans")
@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    /**
     * 여행계획 상세 조회
     * @param planNo 조회할 계획 번호 (PK)
     *
     * @return 계획 번호에 해당하는 계획 (관광지 포함)
     */
    @GetMapping("/{planNo}")
    public ResponseEntity<PlanResponseDto> getPlanByPlanNo(@PathVariable int planNo) {
        PlanResponseDto plan = planService.getPlanByPlanNo(planNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(plan);
    }

    /**
     * 여행 계획 전체 조회
     *
     * @return 모든 여행 계획들
     */
    @GetMapping
    public ResponseEntity<PlanListResponseDto> getPlanList(@RequestParam Map<String, String> map) {
        PlanListResponseDto planList = planService.getPlanList(map);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(planList);
    }


    /**
     * 작성자 아이디로 여행 계획 전체 조회
     * ${root}/plans?id={id}
     *
     * @param id 여행 계획 작성자 아이디
     * @return 아이디에 해당하는 회원이 작성한 여행 계획 리스트
     */
    @GetMapping(params = "id")
    @CheckToken
    public ResponseEntity<List<PlanResponseDto>> getPlanListById(@RequestParam String id) {
        List<PlanResponseDto> planList = planService.getPlanListById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(planList);
    }


    /**
     * 여행계획 등록
     *
     * @param requestDto
     * @return 201 CREATED + 등록된 여행계획 테이블의 PK 값
     * @author yihoney
     */
    @PostMapping
    @CheckToken
    public ResponseEntity<PlanNoResponseDto> createPlan(@RequestBody PlanRequestDto requestDto) {
        int planNo = planService.createPlan(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PlanNoResponseDto(planNo));
    }

    /**
     * 여행계획 수정
     *
     * @param requestDto
     * @return 200 OK + 수정된 여행계획 테이블의 PK 값
     * @author yihoney
     */
    @PutMapping
    @CheckToken
    public ResponseEntity<PlanNoResponseDto> modifyPlan(@RequestBody PlanRequestDto requestDto) {
        int planNo = planService.modifyPlan(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PlanNoResponseDto(planNo));
    }

    /**
     * 여행계획 삭제
     *
     * @param planNo
     * @return 200 OK
     * @author yihoney
     */
    @PostMapping("/{planNo}")
    @CheckToken
    public ResponseEntity<Void> deletePlan(@PathVariable int planNo) {
        planService.deletePlan(planNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
