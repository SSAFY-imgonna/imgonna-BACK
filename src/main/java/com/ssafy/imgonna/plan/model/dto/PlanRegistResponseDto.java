package com.ssafy.imgonna.plan.model.dto;

public class PlanRegistResponseDto {
    private int planNo;

    public PlanRegistResponseDto(int planNo) {
        this.planNo = planNo;
    }

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }
}
