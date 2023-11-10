package com.ssafy.trip.qna.inquiry.model.dto;

import java.util.List;

public class InquiryListResponseDto {
    private List<InquiryResponseDto> inquiryList;
    private int currentPage;
    private int totalPageCount;

    public List<InquiryResponseDto> getInquiryList() {
        return inquiryList;
    }

    public void setInquiryList(List<InquiryResponseDto> inquiryList) {
        this.inquiryList = inquiryList;
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
