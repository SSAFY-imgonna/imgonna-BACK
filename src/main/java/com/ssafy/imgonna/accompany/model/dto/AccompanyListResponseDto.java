package com.ssafy.imgonna.accompany.model.dto;

import java.util.List;


public class AccompanyListResponseDto {
    private List<AccompanyResponseDto> accompanyList;
    private int currentPage;
    private int totalPageCount;
    
    
	public List<AccompanyResponseDto> getAccompanyList() {
		return accompanyList;
	}
	public void setAccompanyList(List<AccompanyResponseDto> accompanyList) {
		this.accompanyList = accompanyList;
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
