package com.ssafy.imgonna.plan.model.dto;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;

import java.util.List;

public class PlanRequestDto {
    private int planNo;
    private String name;
    private String startTime;
    private String departureTime;
    private String id;
    private List<AttractionInfo> attractions;
    private int themeNo;
    private String memo;
    private List<String> detailMemoList;

    public List<String> getDetailMemoList() {
        return detailMemoList;
    }

    public void setDetailMemoList(List<String> detailMemoList) {
        this.detailMemoList = detailMemoList;
    }

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AttractionInfo> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<AttractionInfo> attractions) {
        this.attractions = attractions;
    }

    public int getThemeNo() {
        return themeNo;
    }

    public void setThemeNo(int themeNo) {
        this.themeNo = themeNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
