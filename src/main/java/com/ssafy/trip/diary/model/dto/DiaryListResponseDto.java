package com.ssafy.trip.diary.model.dto;

import java.util.List;


public class DiaryListResponseDto {
    private List<DiaryResponseDto> diaryList;
    private int currentPage;
    private int totalPageCount;

    public List<DiaryResponseDto> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<DiaryResponseDto> diaryList) {
        this.diaryList = diaryList;
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
