package com.ssafy.trip.accompany.model;

public class AccompanyJoinDto {
	int accompanyNo;
	String id;
	
	public int getAccompanyNo() {
		return accompanyNo;
	}
	public void setAccompanyNo(int accompanyNo) {
		this.accompanyNo = accompanyNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "AccompanyJoinDto [accompanyNo=" + accompanyNo + ", id=" + id + "]";
	}
}
