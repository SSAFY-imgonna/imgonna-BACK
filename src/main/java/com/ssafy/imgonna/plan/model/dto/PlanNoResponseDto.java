package com.ssafy.imgonna.plan.model.dto;

public class PlanNoResponseDto {
    private int planNo;

    public PlanNoResponseDto(int planNo) {
        this.planNo = planNo;
    }

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }
}
