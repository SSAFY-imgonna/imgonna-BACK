package com.ssafy.imgonna.plan.model.dto;

import java.util.List;

public class PlanResponseDto {
    private int planNo;
    private String name;
    private String startTime;
    private String departureTime;
    private String id;
    private int themeNo;
    private String memo;
    private List<CourseResponseDto> courses;

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

    public List<CourseResponseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResponseDto> courses) {
        this.courses = courses;
    }
}
