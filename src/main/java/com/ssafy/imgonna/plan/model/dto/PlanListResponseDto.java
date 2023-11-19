package com.ssafy.imgonna.plan.model.dto;

import java.util.List;


public class PlanListResponseDto {
    private List<PlanResponseDto> planList;
    private int currentPage;
    private int totalPageCount;

    public List<PlanResponseDto> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PlanResponseDto> planList) {
        this.planList = planList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
