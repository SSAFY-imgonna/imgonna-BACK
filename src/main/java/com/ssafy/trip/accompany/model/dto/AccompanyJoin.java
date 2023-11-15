package com.ssafy.trip.accompany.model.dto;

public class AccompanyJoin {
	private int accompanyNo;
	private String id;
	
	
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
		return "AccompanyJoin [accompanyNo=" + accompanyNo + ", id=" + id + "]";
	}
	
}
