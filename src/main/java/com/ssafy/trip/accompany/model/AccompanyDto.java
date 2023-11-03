package com.ssafy.trip.accompany.model;

public class AccompanyDto {
	private int accompanyNo;
	private String accompanyTitle;
	private String accompanyContent;
	private String accompanyLoc;
	private String accompanyDate;
	private int accompanyNum;
	private int accompanyTotal;
	private String accompanyPhoto; // 업로드된 파일을 저장하는 String 객체
	private int hit;
	private String id;
	private String nickname;
	private String regDate;
	
	public int getAccompanyNo() {
		return accompanyNo;
	}
	public void setAccompanyNo(int accompanyNo) {
		this.accompanyNo = accompanyNo;
	}
	public String getAccompanyTitle() {
		return accompanyTitle;
	}
	public void setAccompanyTitle(String accompanyTitle) {
		this.accompanyTitle = accompanyTitle;
	}
	public String getAccompanyContent() {
		return accompanyContent;
	}
	public void setAccompanyContent(String accompanyContent) {
		this.accompanyContent = accompanyContent;
	}
	public String getAccompanyLoc() {
		return accompanyLoc;
	}
	public void setAccompanyLoc(String accompanyLoc) {
		this.accompanyLoc = accompanyLoc;
	}
	public String getAccompanyDate() {
		return accompanyDate;
	}
	public void setAccompanyDate(String accompanyDate) {
		this.accompanyDate = accompanyDate;
	}
	public int getAccompanyNum() {
		return accompanyNum;
	}
	public void setAccompanyNum(int accompanyNum) {
		this.accompanyNum = accompanyNum;
	}
	public int getAccompanyTotal() {
		return accompanyTotal;
	}
	public void setAccompanyTotal(int accompanyTotal) {
		this.accompanyTotal = accompanyTotal;
	}
	public String getAccompanyPhoto() {
		return accompanyPhoto;
	}
	public void setAccompanyPhoto(String accompanyPhoto) {
		this.accompanyPhoto = accompanyPhoto;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getId() {
		return id;
	}
	public void setUserId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setUserNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "AccompanyDto [accompanyNo=" + accompanyNo + ", accompanyTitle=" + accompanyTitle + ", accompanyContent="
				+ accompanyContent + ", accompanyLoc=" + accompanyLoc + ", accompanyDate=" + accompanyDate
				+ ", accompanyNum=" + accompanyNum + ", accompanyTotal=" + accompanyTotal + ", accompanyPhoto="
				+ accompanyPhoto + ", hit=" + hit + ", id=" + id + ", nickname=" + nickname + ", regDate=" + regDate
				+ "]";
	}
}
