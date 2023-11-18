package com.ssafy.imgonna.plan.controller;

import com.ssafy.imgonna.plan.model.dto.PlanRegistRequestDto;
import com.ssafy.imgonna.plan.model.dto.PlanRegistResponseDto;
import com.ssafy.imgonna.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/plans")
@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }


    @PostMapping
    public ResponseEntity<PlanRegistResponseDto> createAccompany(@RequestBody  PlanRegistRequestDto requestDto) throws Exception {
        int planNo = planService.createPlan(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PlanRegistResponseDto(planNo));
    }


}
